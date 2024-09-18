package Book;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SampleFormTuto1 extends Application {

  public static void main(String[] args) {
    Application.launch(args);
  }
  
  public void start(Stage primaryStage) throws Exception {
          // Create a label to display the entered text.
          Label resultLabel = new Label("Result will be shown here.");
          // Create labels and text fields for input.
          Label lblName = new Label("Name:");
          Label lblMkpt = new Label("MKPT:");
          TextField tfName = new TextField();
          TextField tfMkpt = new TextField();
          Label lblGrade = new Label("Grade:");
          Label lblGender = new Label("Gender:");
          Label lblEnrolled = new Label("Enrolled:");
          Button displayButton = new Button("Show");

          // Create a ComboBox for selecting a grade.
          ComboBox<String> cbGrade = new ComboBox<>();
          cbGrade.getItems().addAll("A", "B", "C", "D", "E");
          cbGrade.setValue("A");
          
          // Create RadioButtons for gender selection.
          RadioButton rbMale = new RadioButton("Male");
          RadioButton rbFemale = new RadioButton("Female");
          ToggleGroup genderGroup = new ToggleGroup();
          rbMale.setToggleGroup(genderGroup);
          rbFemale.setToggleGroup(genderGroup);
          
          // Create CheckBox for enrollment status.
          CheckBox chkEnrolled = new CheckBox();

          // Set an action for the button to display the entered text.
          displayButton.setOnAction(e -> {
              String enteredText = "Name:\t" + tfName.getText() + "\n" +
                                   "MKPT:\t" + tfMkpt.getText() + "\n" +
                                   "Grade:\t" + cbGrade.getValue() + "\n" +
                                   "Gender:\t" + (rbMale.isSelected() ? "Male" : rbFemale.isSelected() ? "Female" : "Not selected") + "\n" +
                                   "Enrolled:\t" + (chkEnrolled.isSelected() ? "Yes" : "No");
              resultLabel.setText("Input Text: \n" + enteredText);
          });

          // Create a GridPane layout and add the components to it.
          GridPane grid = new GridPane();
          grid.setHgap(10); // Horizontal gap between columns
          grid.setVgap(10); // Vertical gap between rows
          grid.setAlignment(Pos.CENTER);

          // Add labels and components to the grid with specified column and row positions
          grid.add(lblName, 0, 0); // Column 0, Row 0
          grid.add(tfName, 1, 0); // Column 1, Row 0
          grid.add(lblMkpt, 0, 1); // Column 0, Row 1
          grid.add(tfMkpt, 1, 1); // Column 1, Row 1
          grid.add(lblGrade, 0, 2); // Column 0, Row 2
          grid.add(cbGrade, 1, 2); // Column 1, Row 2
          grid.add(lblGender, 0, 3); // Column 0, Row 3
          grid.add(rbMale, 1, 3); // Column 1, Row 3
          grid.add(rbFemale, 1, 4); // Column 1, Row 4
          grid.add(lblEnrolled, 0, 5); // Column 0, Row 5
          grid.add(chkEnrolled, 1, 5); // Column 1, Row 5
          grid.add(displayButton, 0, 6, 2, 1); // Column 0, Row 6, span 2 columns
          grid.add(resultLabel, 0, 7, 2, 1); // Column 0, Row 7, span 2 columns

          // Create the scene and set it in the stage.
          Scene scene = new Scene(grid, 400, 300);
          primaryStage.setTitle("Student Info");
          primaryStage.setScene(scene);
          primaryStage.show();
  }

}
