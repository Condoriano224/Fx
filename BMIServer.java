package Exercise;

import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;


public class BMIServer extends Application{
	
	final double KILOGRAMS_PER_POUND = 0.45359237; 
	final double METERS_PER_INCH = 0.0254; 
  
	public void start(Stage primaryStage){
		TextArea ta = new TextArea();
		Scene scene = new Scene(new ScrollPane(ta), 450, 200);
		primaryStage.setTitle("BMI Server");
		primaryStage.setScene(scene); 
		primaryStage.show();

    new Thread(() ->{
    	try{
        
    		ServerSocket serverSocket = new ServerSocket(8002);
    		Platform.runLater(() ->
    		ta.appendText("BMI Server started at " + new Date() + '\n'));

    		Socket socket = serverSocket.accept();
    		ta.appendText("Connected to a client at " +new Date() + '\n');
    		
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            while (true) {
            	double weight = input.readDouble();
            	double height = input.readDouble();

            	double weightInKilo = weight * KILOGRAMS_PER_POUND;
            	double heightInMeter = height * METERS_PER_INCH;
            	double bmi = weightInKilo/ Math.pow(heightInMeter, 2);

            	StringBuilder strBMI = new StringBuilder("BMI is " + String.format("%.2f", bmi)+".\t");
          
            	if (bmi < 18.5)
            		strBMI.append("Underweight");
            	else if (bmi < 25)
            		strBMI.append("Normal");
            	else if (bmi < 30)
            		strBMI.append("Overweight");
            	else
            		strBMI.append("Obese");

            	output.writeUTF(strBMI.toString());

            	Platform.runLater(() -> {
            		ta.appendText("Weight: " + weight + '\n');
            		ta.appendText("Height: " + height + '\n');
            		ta.appendText(strBMI.toString() + '\n');
            	});
            }
      }catch (IOException ex) {
        ex.printStackTrace();
      }
    }).start();
  }
  public static void main(String args[]){
    Application.launch(args);
  }
}