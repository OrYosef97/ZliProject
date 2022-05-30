package gui;

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

public class WorkerMainScreenController {

    @FXML
    private Button logOutBtn;

    @FXML
    private Button xBtn;
    
    User user;

    @FXML
    void EnterSurveyComments(ActionEvent event) {
    	try {
    	//	ClientUI.chat.accept(new Message(ClientMessageType.UpdateLoggedIn,user.getUserName()));
			((Node) event.getSource()).getScene().getWindow().hide();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/EnterSurveyCommentsScreen.fxml"));
			Pane root = loader.load();
			Stage primaryStage = new Stage();
			Image icon = new Image("/gui/icon1.jpeg");
			primaryStage.getIcons().add(icon);
			Scene scene = new Scene(root);
			EnterSurveyCommentsScreenController esc = loader.getController();
			esc.SetUser(user);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    

    @FXML
    void LogOut(ActionEvent event) {
    	try {
    		ClientUI.chat.accept(new Message(ClientMessageType.UpdateLoggedIn,user.getUserName()+" 0"));
			((Node) event.getSource()).getScene().getWindow().hide();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LoginScreen.fxml"));
			Pane root = loader.load();
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
    void UpdateCatalog(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {
    	ClientUI.chat.accept(new Message(ClientMessageType.EXIT,user.getUserName())); //loggedin = 0
    	System.exit(0);
    }
    
    public void SetUser(User user) {
		this.user = user;
	}

}
