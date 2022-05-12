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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import logic.Order;

public class ClientMainScreenController {

	@FXML
	private Button EnterBtn;

	@FXML
	private Label errorLable;

	@FXML
	private TextField orderNumberTxtFiled;

	@FXML
	private Button xBtn;
	
    @FXML
    private Button viewOrdersBtn;

	@FXML
	void enter(ActionEvent event) throws IOException {
		String orderNum;// ,command;
		Order order = null;

		orderNum = orderNumberTxtFiled.getText().trim();
		if (orderNum.isEmpty()) {
			System.out.println("You must enter an Order number");
			errorLable.setText("You must enter an Order number");
			errorLable.setVisible(true);

		} else {

			// command="getOrder "+orderNum.trim();
			try {

				ClientUI.chat.accept("getOrder " + orderNum);

				order = (Order) ClientChat.returnedValueFromServer;

			} catch (Exception e) {
				e.printStackTrace();
			}

			if (order == null) {
				System.out.println("Order Not Found");
				errorLable.setText("Order Not Found");
				errorLable.setVisible(true);

			} else {
				((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/OrderScreen.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root);
				Stage primaryStage = new Stage();
				OrderScreenController orderScreenController = loader.getController();
				orderScreenController.loadOrder(order);
				Image icon = new Image("/gui/icon1.jpeg");
				primaryStage.getIcons().add(icon);
				primaryStage.setScene(scene);
				primaryStage.setTitle("OrderScreen");
				primaryStage.show();

			}
		}

	}

	@FXML
	void viewOrders(ActionEvent event) throws IOException {
//		ClientUI.chat.accept("getOrders");
//		String orders = (String) ClientChat.returnedValueFromServer;
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/viewOrdersScreen.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		ViewOrdersScreenController viewOrdersScreenController = loader.getController();
		viewOrdersScreenController.loadOrders();
		Image icon = new Image("/gui/icon1.jpeg");
		primaryStage.getIcons().add(icon);
		primaryStage.setScene(scene);
		primaryStage.setTitle("OrderScreen");
		primaryStage.show();

	}

	@FXML
	void exit(ActionEvent event) {
		ClientUI.chat.accept("disconnect");
		System.exit(0);
	}
}
