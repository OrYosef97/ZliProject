package gui;

import java.io.IOException;

import client.ClientChat;
import client.ClientUI;
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
			ClientUI.chat.accept("getUserInfo " + usernameTextField.getText().trim());
			String msg = (String) ClientChat.returnedValueFromServer;
			if (msg == null) {// if the user not found - wrong username
				wrongLoginLabel.setText("User dose not exist");
				wrongLoginLabel.setVisible(true);
				return;
			}
			
			String userInfo[] = msg.split("//z"); //test
			System.out.println(userInfo[1]);
			if(!(passwordTextField.getText().trim().equals(userInfo[1]))) {//if password incorrect
				wrongLoginLabel.setText("Wrong username or password");
				wrongLoginLabel.setVisible(true);
				return;	
			}
			if(userInfo[3].equals("1")) {//if password incorrect
				wrongLoginLabel.setText("User already connected");
				wrongLoginLabel.setVisible(true);
				return;	
			}
			((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
			Stage primaryStage = new Stage();
			/*update user info to Logged in*/
			//ClientUI.chat.accept("updateLoggedIn " + usernameTextField.getText().trim() +" 1");
			switch(userInfo[2]){
			case "customer": 
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CustomerMainScreen.fxml"));
				
				Parent root = loader.load();
				loader.getController();
				Scene scene = new Scene(root);
				//Image icon = new Image("/images.img/icon1.jpeg");
				//primaryStage.getIcons().add(icon);
				primaryStage.setTitle("Customer Window");
				primaryStage.setScene(scene);
				primaryStage.show();
				
				break;
				
			case "deliveryPerson": 
				FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/gui/DeliveryPersonScreen.fxml"));
				Parent root2 = loader2.load();
				DeliveryPersonScreenController dc = loader2.getController();
				dc.setRoleName(usernameTextField.getText().trim());
				Scene scene2 = new Scene(root2);
				//Image icon = new Image("/images.img/icon1.jpeg");
				//primaryStage.getIcons().add(icon);
				
				primaryStage.setTitle("Delivery Window");
				primaryStage.setScene(scene2);
				primaryStage.show();
				break;
				
			}

		}

	}

}
