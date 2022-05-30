package gui;

import java.sql.SQLException;

import client.ClientPortal;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ConnectToServerScreenController {

	@FXML
	private Button connectBtn;

	@FXML
	private Label errorLable;

	@FXML
	private TextField hostNameTxtFiled;

	@FXML
	void connect(ActionEvent event) {
		if (hostNameTxtFiled.getText().trim().isEmpty()) {
			errorLable.setText("You must enter host name!");
			errorLable.setVisible(true);
			return;
		} else {
			try {
				ClientUI.chat = new ClientPortal(hostNameTxtFiled.getText().trim(), 5555);
				((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
				Parent root = FXMLLoader.load(getClass().getResource("/gui/LoginScreen.fxml"));
				Image icon = new Image("/gui/icon1.jpeg");
				Stage primaryStage = new Stage();
				primaryStage.getIcons().add(icon);
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (Exception e) {
				e.printStackTrace();
				errorLable.setText("Can't find host");
			}
		}
	}
<<<<<<< Upstream, based on branch 'master' of https://github.com/OrYosef97/ZliProject.git
=======
	 @FXML
	    void EnterPressed(KeyEvent event) {
		 if (event.getCode() == KeyCode.ENTER) {
				connectBtn.fire();
			}
	    }
	
	
>>>>>>> 6a3a052 Worker update + added enter functionality

}