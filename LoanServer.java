package Exercise;

import java.util.*;
import java.net.*;
import java.io.*;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class LoanServer extends Application{
	public void start(Stage primaryStage) {
		
		TextArea ta=new TextArea();
		Scene scene=new Scene(new ScrollPane(ta),450,300);
		primaryStage.setTitle("LoanServer");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		new Thread(()->{ 
			try {
				ServerSocket server=new ServerSocket(8031);
				Platform.runLater(()->
				ta.appendText("Server started at "+new Date()+'\n'));
				
				Socket socket=server.accept();
				ta.appendText("Connected to a client at "+new Date()+'\n');
				
				DataOutputStream output=new DataOutputStream(socket.getOutputStream());
				DataInputStream input=new DataInputStream(socket.getInputStream());
				
				while(true) {
					double annualInterestRate=input.readDouble();
					int numOfYears=input.readInt();
					double amount=input.readDouble();
					
					Platform.runLater(() -> 
					ta.appendText("Annual interest rate: " + annualInterestRate + "\n" + 
	                            "Number of Years: " + numOfYears + "\n" + 
	                            "Loan amount: " + amount + "\n"));
	                    
					double monthlyInterestRate=annualInterestRate/1200;
					double monthlyPayment =amount * monthlyInterestRate / 
			                    (1 -( 1/Math.pow(1 + monthlyInterestRate, numOfYears * 12)));
			        double totalPayment = monthlyPayment * numOfYears * 12;
			                
			        output.writeDouble(monthlyPayment);
			        output.writeDouble(totalPayment);
			        
			        Platform.runLater(()->{
			        ta.appendText("monthlyPayment: "+monthlyPayment+'\n');
			        ta.appendText("totalPayment: "+totalPayment+'\n');
			        });
			      
				}
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}).start();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
