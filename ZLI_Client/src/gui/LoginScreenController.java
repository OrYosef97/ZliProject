package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.ClientChat;
import client.ClientUI;
import common.Message;
import enumType.ClientMessageType;
import enumType.ServerMessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import logic.User;

public class LoginScreenController implements Initializable {

	@FXML
	private Button LoginBtn;

	@FXML
	private PasswordField passwordTextField;

	@FXML
	private TextField usernameTextField;

	@FXML
	private Label wrongLoginLabel;

	@FXML
	private Button xBtn;

	private User user;

	@FXML
	void exit(ActionEvent event) {
		System.exit(0);
	}



	@FXML
	void login(ActionEvent event) throws IOException {
		if (usernameTextField.getText().trim().isEmpty() || passwordTextField.getText().trim().isEmpty()) {
			wrongLoginLabel.setText("Username and Password fields cannot be empty");
			wrongLoginLabel.setVisible(true);
		} else {
			ClientUI.chat.accept(new Message(ClientMessageType.LOGIN, usernameTextField.getText().trim()));
			Message message = (Message) ClientChat.returnedValueFromServer;
			if (message.getServerMessageType() == ServerMessageType.FAILED) {// if the user not found - wrong username
				wrongLoginLabel.setText("User dose not exist");
				wrongLoginLabel.setVisible(true);
				return;
			}

			user = (User) message.getObj();
			System.out.println("just for check " + user);
			if (!(passwordTextField.getText().trim().equals(user.getPassword()))) {// if password incorrect
				wrongLoginLabel.setText("Wrong username or password");
				wrongLoginLabel.setVisible(true);
				return;
			}
			if (user.getIsLoggedIn() == 1) {// if User already connected
				wrongLoginLabel.setText("User already connected");
				wrongLoginLabel.setVisible(true);
				return;
			}
			((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
			Stage primaryStage = new Stage();
			/* update user info to Logged in */
			user.setUserName(usernameTextField.getText());
		//	ClientUI.chat.accept(new Message(ClientMessageType.UpdateLoggedIn, user.getUserName() + " 1"));
			switch (user.getUserType()) {
			case CUSTOMER:
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CustomerMainScreen1.fxml"));

				Parent root = loader.load();
				CustomerMainScreenController cmmSC = loader.getController();
				cmmSC.setUser(user);
				Scene scene = new Scene(root);
				// Image icon = new Image("/images.img/icon1.jpeg");
				// primaryStage.getIcons().add(icon);
				primaryStage.setTitle("Customer Window");
				primaryStage.setScene(scene);
				primaryStage.show();

				break;

			case DELIVERY_PERSON:
				FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/gui/DeliveryPersonScreen.fxml"));
				Parent root2 = loader2.load();
				DeliveryPersonScreenController dc = loader2.getController();
				dc.setUser(user);
				// dc.setRoleName(usernameTextField.getText().trim());
				Scene scene2 = new Scene(root2);
				// Image icon = new Image("/images.img/icon1.jpeg");
				// primaryStage.getIcons().add(icon);

				primaryStage.setTitle("Delivery Window");
				primaryStage.setScene(scene2);
				primaryStage.show();
				break;
			case CUSTOMER_SERVICE_WORKER:
				loader2 = new FXMLLoader(getClass().getResource("/gui/CustomerServiceWorkerMainScreen.fxml"));
				root2 = loader2.load();
				CustomerServiceWorkerMainScreenController csw = loader2.getController();
				csw.SetUser(user);
				// dc.setRoleName(usernameTextField.getText().trim());
				scene2 = new Scene(root2);
				// Image icon = new Image("/images.img/icon1.jpeg");
				// primaryStage.getIcons().add(icon);

				primaryStage.setTitle("");
				primaryStage.setScene(scene2);
				primaryStage.show();
				break;

			case CEO:
				break;
			case STORE_MANAGER:
				loader = new FXMLLoader(getClass().getResource("/gui/StoremanagerMainScreen.fxml"));
				root = loader.load();
				scene = new Scene(root);
				primaryStage.setTitle("Store Manager Main Screen");
				primaryStage.setScene(scene);
				primaryStage.show();
				break;

			case WORKER:
				loader = new FXMLLoader(getClass().getResource("/gui/WorkerMainScreen.fxml"));
				root = loader.load();
				scene = new Scene(root);
				WorkerMainScreenController wc = loader.getController();
				wc.SetUser(user);
				primaryStage.setScene(scene);
				primaryStage.show();
				break;
			default:
				break;

			}

		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		wrongLoginLabel.setVisible(false);

	}

	@FXML
	void EnterPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			LoginBtn.fire();
		}
	}

}
