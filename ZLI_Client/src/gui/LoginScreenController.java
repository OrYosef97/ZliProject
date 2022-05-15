package gui;

import java.io.IOException;
import java.util.ArrayList;

import client.ClientChat;
import client.ClientUI;
import common.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import enumType.*;
public class LoginScreenController {

	@FXML
	private Button LoginBtn;

	@FXML
	private PasswordField passwordTextField;

	@FXML
	private TextField usernameTextField;

	@FXML
	private Label wrongLoginLabel;

	@FXML
	void login(ActionEvent event) throws IOException {
		if (usernameTextField.getText().trim().isEmpty() || passwordTextField.getText().trim().isEmpty()) {
			wrongLoginLabel.setText("Username and Password fields cannot be empty");
			wrongLoginLabel.setVisible(true);
		} else {
			Message message=new Message(ClientMessage.LOGIN,DBControllerType.GeneralDBController, (Object)usernameTextField.getText().trim()+passwordTextField.getText().trim());
			ClientUI.chat.accept(message);
			message = (Message) ClientChat.returnedValueFromServer;
			if (message.getObj() == null) {// if the user not found - wrong username
				wrongLoginLabel.setText("User dose not exist");
				wrongLoginLabel.setVisible(true);
				return;
			}
			String userInfo[] = ((String) message.getObj()).split("//z");
			System.out.println(userInfo[1]);
			if(!(passwordTextField.getText().trim()==userInfo[1])) {//if password incorrect
				wrongLoginLabel.setText("Wrong username or password");
				wrongLoginLabel.setVisible(true);
				return;	
			}
			if(userInfo[3]=="1") {//if password incorrect
				wrongLoginLabel.setText("User already connected");
				wrongLoginLabel.setVisible(true);
				return;	
			}
			((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
			Stage primaryStage = new Stage();
			switch(userInfo[2]){
			case "customer": 
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ConnectToServerScreen.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root);
				Image icon = new Image("/images.img/icon1.jpeg");
				primaryStage.getIcons().add(icon);
				primaryStage.setTitle("Zli Server Window");
				primaryStage.setScene(scene);
				primaryStage.show();
				
			}

		}

	}

}
