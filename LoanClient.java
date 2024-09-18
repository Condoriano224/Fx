package Exercise;

import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoanClient extends Application{
	
	DataInputStream input=null;
	DataOutputStream output=null;
	
	public void start(Stage primaryStage) {
		 
		 TextArea ta = new TextArea();
		 BorderPane borderPane = new BorderPane();
		 borderPane.setCenter(new ScrollPane(ta));
	        
		 TextField tfRate = new TextField();   
		 TextField tfYears = new TextField();
		 TextField tfAmount = new TextField();
	     tfRate.setPrefColumnCount(10);
	     tfYears.setPrefColumnCount(10);
	     tfAmount.setPrefColumnCount(10);
	     tfRate.setAlignment(Pos.BASELINE_RIGHT);
	     tfAmount.setAlignment(Pos.BASELINE_RIGHT);
	     tfYears.setAlignment(Pos.BASELINE_RIGHT);
	     
	     Button btSubmit = new Button("Submit");
	     
	     GridPane grid = new GridPane();  
	     grid.add(new Label("Annual Interest Rate"), 0, 0);
	     grid.add(tfRate, 1, 0);
	     grid.add(new Label("Number Of Years"), 0, 1);
	     grid.add(tfYears, 1, 1);
	     grid.add(btSubmit, 2, 1);
	     grid.add(new Label("Loan Amount"), 0, 2);
	     grid.add(tfAmount, 1, 2);
	     borderPane.setTop(grid);   
	        
		Scene scene=new Scene(borderPane,450,300);
		primaryStage.setTitle("LoanClient");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		btSubmit.setOnAction(e-> {
		try {
            double interestRate = Double.parseDouble(tfRate.getText().trim());
            int numberOfYears = Integer.parseInt(tfYears.getText().trim());
            double amount = Double.parseDouble(tfAmount.getText().trim());
            
            output.writeDouble(interestRate);
            output.writeInt(numberOfYears);
            output.writeDouble(amount);
            output.flush();
            
            ta.appendText("Annual interest rate: " + interestRate + "\n" + 
                        "Number of Years: " + numberOfYears + "\n" + "Loan amount: " + amount + "\n");
            
            double monthlyPayment = input.readDouble();
            double totalPayment = input.readDouble();
            ta.appendText("monthlyPayment: " + monthlyPayment + "\n" + 
                        "totalPayment: " + totalPayment + "\n");
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
    });

		try {
			Socket socket=new Socket("localhost",8031);
			input=new DataInputStream(socket.getInputStream());
			output=new DataOutputStream(socket.getOutputStream());
		}catch(IOException ex) {
			ta.appendText(ex.toString()+'\n');
		}
		
	}
	public static void main(String [] args) {
		Application.launch(args);
	}
}
