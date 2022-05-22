package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class mainForCatalog extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CustomerMainScreen1.fxml"));
			Parent root = loader.load();
			Image icon = new Image("/gui/icon1.jpeg");
			primaryStage.getIcons().add(icon);
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
