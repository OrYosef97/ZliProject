package server;

import java.io.IOException;

import gui.ServerScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ServerUI extends Application {
	final public static int DEFAULT_PORT = 5555;
	public static ServerScreenController aFrame;
	static EchoServer sv;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ServerScreen.fxml"));
		Parent root = loader.load();
		aFrame = loader.getController(); // create ServerFrame
		Scene serverScene = new Scene(root);
		//serverScene.getStylesheets().add(getClass().getResource("/gui/serverScreen.css").toExternalForm());
		Image icon = new Image("/gui/icon1.jpeg");
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("Zli Server Window");
		primaryStage.setScene(serverScene);
		primaryStage.show();

	}

	public static void main(String args[]) throws Exception {
		launch(args);
	} // end main

	public static void runServer(String p) {
		int port = 0; // Port to listen on

		try {
			port = Integer.parseInt(p); // Set port to 5555

		} catch (Throwable t) {
			System.out.println("ERROR - Could not connect!");
			//aFrame.setTextToConsole("ERROR - Could not connect!");//new
		}

		 sv = new EchoServer(port);
		try {

			sv.listen(); // Start listening for connections
			//aFrame.setTextToConsole("Server listening for connections on port ");//new

		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		
			//aFrame.setTextToConsole("ERROR - Could not listen for clients! ");//new
		}
	}
	
	public static void closeServer() {
		try {
			sv.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Cant close the server connection");
		}
		
	}

}
