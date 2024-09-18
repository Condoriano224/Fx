package Listing;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class StudentServer {
	
	private ObjectOutputStream output;
	private ObjectInputStream input;
	
	 public static void main(String[] args) {
		 new StudentServer();
	 }
	 public StudentServer(){
		try{
			 ServerSocket serverSocket = new ServerSocket(8010);
			 System.out.println("Server started ");
			 output = new ObjectOutputStream(new FileOutputStream("student.dat",true));
			 
			 while(true){
				 Socket socket = serverSocket.accept();
				 input =new ObjectInputStream(socket.getInputStream());
				 Object object = input.readObject();
				 output.writeObject(object); 
				 System.out.println("A new student object is stored");
			 }
		}
		catch(ClassNotFoundException ex) {
			 ex.printStackTrace();
		}
		 catch(IOException ex) {
			 ex.printStackTrace();
		}
		finally{
			try{
				 input.close();
				 output.close();
			}
			catch(Exception ex){
				 ex.printStackTrace();
			}
		}
	 }

}
