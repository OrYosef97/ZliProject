package gui;

import client.ClientChat;
import client.ClientUI;
import common.Message;
import enumType.ClientMessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.User;

public class CustomerServiceWorkerMainScreenController {

    @FXML
    private Button logOutBtn;

    @FXML
    private Button xBtn;

    @FXML
    private Button createOrderBtn;
    
    User user;

    @FXML
    void AddComplaint(ActionEvent event) {
    	try {
			((Node) event.getSource()).getScene().getWindow().hide();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AddComplaintsScreen.fxml"));
			Pane root = loader.load();
			// ClientMainScreenController clientMainScreenController =
			// loader.getController();
			// clientMainScreenController.setErrorTxtFVisability(false);
			Stage primaryStage = new Stage();
			Image icon = new Image("/gui/icon1.jpeg");
			primaryStage.getIcons().add(icon);
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("background.css").toExternalForm());
			primaryStage.setScene(scene);
			AddComplaintsScreenController ac = new AddComplaintsScreenController ();
			ac.SetUser(user);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    

    @FXML
    void LogOut(ActionEvent event) {
    	try {
    		//System.out.println(userName);
    		ClientUI.chat.accept(new Message(ClientMessageType.UpdateLoggedIn,user.getUserName()));
			((Node) event.getSource()).getScene().getWindow().hide();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LoginScreen.fxml"));
			Pane root = loader.load();
			// ClientMainScreenController clientMainScreenController =
			// loader.getController();
			// clientMainScreenController.setErrorTxtFVisability(false);
			Stage primaryStage = new Stage();
			Image icon = new Image("/gui/icon1.jpeg");
			primaryStage.getIcons().add(icon);
			Scene scene = new Scene(root);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void exit(ActionEvent event) {
    	ClientUI.chat.accept(new Message(ClientMessageType.EXIT,user.getUserName())); //loggedin = 0
    	String s = (String)ClientChat.returnedValueFromServer;
    	System.exit(0);
    }
    
    @FXML
    void SaveSurveyConclutions(ActionEvent event) {
    	try {
			((Node) event.getSource()).getScene().getWindow().hide();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/SurveyConclusionsScreen.fxml"));
			Pane root = loader.load();
			// ClientMainScreenController clientMainScreenController =
			// loader.getController();
			// clientMainScreenController.setErrorTxtFVisability(false);
			Stage primaryStage = new Stage();
			Image icon = new Image("/gui/icon1.jpeg");
			primaryStage.getIcons().add(icon);
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("background.css").toExternalForm());
			primaryStage.setScene(scene);
			AddComplaintsScreenController ac = new AddComplaintsScreenController ();
			ac.SetUser(user);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void SetUser(User user) {
		this.user = user;
	}
}
