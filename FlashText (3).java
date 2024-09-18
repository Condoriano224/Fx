package ch30;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FlashText extends Application{
	private String text="hello";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		StackPane pane=new StackPane();
		Label lb=new Label("Programming is fun");
		pane.getChildren().add(lb);
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try
				{
					while(true)
					{
						if(lb.getText().trim().length() == 0) text="Welcome";
						else text="";
						Platform.runLater(new Runnable(){

							@Override
							public void run() {
								// TODO Auto-generated method stub
								lb.setText(text);
							}
							
						});
						Thread.sleep(200);
					}
				}
				catch(InterruptedException e){
					
				}
				
			}
			
		}).start();
		//Create scene
		Scene scene=new Scene(pane,450,200);
		primaryStage.setTitle("Flash Text");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
