package Exercise;

import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BMIClient extends Application{
  
	DataOutputStream output = null;
	DataInputStream input = null;

	public void start(Stage primaryStage){
		BorderPane pane = new BorderPane();
		TextArea ta = new TextArea();
		pane.setCenter(new ScrollPane(ta));
		
		TextField tfWeight = new TextField();
		TextField tfHeight = new TextField();
		tfWeight.setAlignment(Pos.BASELINE_RIGHT);
		tfHeight.setAlignment(Pos.BASELINE_RIGHT);

		Button btSubmit = new Button("Submit");

		GridPane grid = new GridPane();
		grid.add(new Label("Weight in pounds"), 0, 0);
		grid.add(tfWeight, 1, 0);
		grid.add(new Label("Height in inches"), 0, 1);
		grid.add(tfHeight, 1, 1);
		grid.add(btSubmit, 2, 1);    
		pane.setTop(grid);
    
		Scene scene = new Scene(pane, 400, 200);
		primaryStage.setTitle("BMI Client");
		primaryStage.setScene(scene); 
		primaryStage.show(); 

		btSubmit.setOnAction(e -> {
		try{
			double weight = Double.parseDouble(tfWeight.getText().trim());
			double height = Double.parseDouble(tfHeight.getText().trim());

			output.writeDouble(weight);
			output.writeDouble(height);
			output.flush();

			String bmi = input.readUTF();

			ta.appendText("Weight: " + weight + '\n');
			ta.appendText("Height: " + height + '\n');
			ta.appendText(bmi + '\n');
		}catch (IOException ex) {
			System.err.println(ex);
		}
    });
    try {
    	Socket socket = new Socket("localhost", 8002);
    	input = new DataInputStream(socket.getInputStream());
    	output = new DataOutputStream(socket.getOutputStream());
    }catch (IOException ex) {
    	ta.appendText(ex.toString() + '\n');
    }
    }
	public static void main(String args[]){
		Application.launch(args);
	}
}