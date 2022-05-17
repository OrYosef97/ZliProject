package gui;

import java.io.IOException;

import client.ClientUI;
import common.Message;
import enumType.ClientMessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CustomerMainScreenController {

    @FXML
    private Button canelOrderBtn;

    @FXML
    private Button createOrderBtn;

    @FXML
    private Button logOutBtn;

    @FXML
    private Button xBtn;
    
    String userName;

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@FXML
    void OpenCancelOrderScreen(ActionEvent event) {//to ilan's screen

    }

    @FXML
    void OpenCatalogScreen(ActionEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CreateOrderScreen.fxml"));
		Parent root = loader.load();
		CreatOrderScreenController coSC = loader.getController();
		coSC.setUserName(userName);
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("Creat Order Window");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    @FXML
    void OpenLogInScreen(ActionEvent event) throws IOException {
    	System.out.println("opening loging screen and disconnecting user");
    	ClientUI.chat.accept(new Message(ClientMessageType.EXIT,userName+" 0")); //loggedin = 0
    	
    	((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CreateOrderScreen.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("Login Window");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    @FXML
    void exit(ActionEvent event) {
    	System.out.println();
    	ClientUI.chat.accept(new Message(ClientMessageType.EXIT,userName+" 0")); //loggedin = 0
    	System.exit(0);
    }

}
