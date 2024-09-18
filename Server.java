package Listing;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Server extends Application{
 	public void start (Stage primaryStage){
		TextArea ta=new TextArea();
		ScrollPane pane=new ScrollPane(ta);
		Scene scene=new Scene(pane,450,200);
		primaryStage.setTitle("Server");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		new Thread(()->{
			try{
				ServerSocket serverSocket=new ServerSocket(8001);
				Platform.runLater(()->//for show date
				ta.appendText("Server started at "+new Date()+'\n')
				);
				
				Socket socket=serverSocket.accept();//must
				
				DataInputStream inputFromClient=new DataInputStream(socket.getInputStream());
				DataOutputStream outputToClient=new DataOutputStream(socket.getOutputStream());
				
				while(true){
					double radius=inputFromClient.readDouble();
					double area=radius*radius*Math.PI;
					outputToClient.writeDouble(area);
					Platform.runLater(()->{ //for UI
						ta.appendText("Radius recieved form client: "+radius+'\n');
						ta.appendText("Area is: "+area+'\n');
					});
				}
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
		}).start();
	}
	public static void main(String[]args){
		Application.launch(args);
	}

}
