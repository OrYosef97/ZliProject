package gui;

import java.net.URL;
import java.util.ResourceBundle;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DeliveryPersonScreenController {

    @FXML
    private Button logOutBtn;

    @FXML
    private Button xBtn;

    @FXML
    private Button createOrderBtn;
    
    @FXML
    private Label roleName;
    
   

    @FXML
    void ChooseOrder(ActionEvent event) {
    	try {
			((Node) event.getSource()).getScene().getWindow().hide();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/OrdersScreen.fxml"));
			Pane root = loader.load();
			// ClientMainScreenController clientMainScreenController =
			// loader.getController();
			// clientMainScreenController.setErrorTxtFVisability(false);
			Stage primaryStage = new Stage();
			Image icon = new Image("/gui/icon1.jpeg");
			primaryStage.getIcons().add(icon);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("background.css").toExternalForm());
			primaryStage.setScene(scene);
		    OrdersScreenController oc =loader.getController();
			oc.loadOrders();
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void LogOut(ActionEvent event) {
    	try {
    		ClientUI.chat.accept("updateLoggedIn " + roleName.getText() +" 0");
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
    	System.out.println();
    	ClientUI.chat.accept("updateLoggedIn " + roleName.getText() +" 0"); //loggedin = 0
    	System.exit(0);
    }

	public void setRoleName(String roleName) {
		this.roleName.setText(roleName);;
	}

}
