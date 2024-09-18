package Book;

import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AddressBookGUI extends Application{
	AddressBook addressBook;
	TextField tfName;
	TextField tfStreet;
	TextField tfCity;
	TextField tfState;
	TextField tfPhoneNo;
	
  public void start(Stage primaryStage){
	  Label nameLabel=new Label("Name");
	  Label streetLabel=new Label("Street");
	  Label cityLabel=new Label("City");
	  Label stateLabel=new Label("State");
	  Label zipLabel=new Label("Phone Number");
	  
	  tfName=new TextField();
	  tfStreet=new TextField();
	  tfCity=new TextField();
	  tfState=new TextField();
	  tfPhoneNo =new TextField();
	  
	  tfName.setAlignment(Pos.BOTTOM_RIGHT);
	  tfStreet.setAlignment(Pos.BOTTOM_RIGHT);
	  tfCity.setAlignment(Pos.BOTTOM_RIGHT);
	  tfState.setAlignment(Pos.BOTTOM_RIGHT);
	  tfPhoneNo.setAlignment(Pos.BOTTOM_RIGHT);
	  
	  Button saveButton=new Button("Save");
	  Button cancelButton=new Button("Cancel");
	  saveButton.setOnAction(e -> addAddress());
	  cancelButton.setOnAction(e -> clearData());
	  
	  HBox buttonBox=new HBox(5);
	  buttonBox.getChildren().addAll(saveButton,cancelButton);
	  
	  GridPane grid=new GridPane();
	  grid.setPadding(new Insets(10,10,10,10));
	  grid.setVgap(8);
	  grid.setHgap(10);
	  grid.add(nameLabel, 0, 0);
	  grid.add(tfName, 1, 0);
	  grid.add(streetLabel, 0, 1);
	  grid.add(tfStreet, 1, 1);
	  grid.add(cityLabel, 0, 2);
	  grid.add(tfCity, 1, 2);
	  grid.add(stateLabel, 0, 3);
	  grid.add(tfState, 1, 3);
	  grid.add(zipLabel, 0, 4);
	  grid.add(tfPhoneNo, 1, 4);
	  grid.add(buttonBox, 1, 5);
	  
    try{
      AddressBook addressBook= new AddressBook("address_book.txt");
    }catch(IOException e){
      e.printStackTrace();
      System.exit(1);
    }
 
    Scene scene = new Scene(grid, 400, 300);
    primaryStage.setTitle("Address Book");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  private void addAddress(){
    try{
      String name=tfName.getText();
      String street=tfStreet.getText();
      String city=tfCity.getText();
      String state=tfState.getText();
      String phoneNo=tfPhoneNo.getText();
      
      Address address=new Address(name, street, city, state, phoneNo);
      addressBook.addAddress(address);
    }catch(IOException e){
      e.printStackTrace();
    }
  }
    private void clearData(){
      tfName.clear();
      tfStreet.clear();
      tfCity.clear();
      tfState.clear();
      tfPhoneNo.clear();
    }
    public static void main(String[]args){
      Application.launch(args);
    }
}