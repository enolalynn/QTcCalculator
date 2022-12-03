package com.enola;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;



public class QtcStart extends Application {
	static Stage original_Stage; 
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("view/QtcStart.fxml"));
			Scene scene = new Scene(root);			

			primaryStage.setScene(scene);
			primaryStage.setTitle("QTc Calculator");
			primaryStage.show();
			original_Stage = primaryStage;
			original_Stage.setResizable(false);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void changeScene(String input_file, String title) throws IOException {
		original_Stage.hide();
		Parent root = FXMLLoader.load(QtcStart.class.getResource(input_file));
		Scene scene = new Scene(root);
		original_Stage.setScene(scene);
		original_Stage.setTitle(title);
		original_Stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
