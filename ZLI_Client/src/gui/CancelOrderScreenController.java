package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import client.ClientChat;
import client.ClientUI;
import common.Message;
import enumType.ClientMessageType;
import enumType.ServerMessageType;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logic.Order;
import logic.User;

public class CancelOrderScreenController implements Initializable {

	@FXML
	private TableView<Order> ordersTable;

	@FXML
	private TableColumn<Order, Integer> orderIdCol;

	@FXML
	private TableColumn<Order, String> branchCol;

	@FXML
	private TableColumn<Order, String> orderInfoCol;

	@FXML
	private TableColumn<Order, String> orderDateCol;

	@FXML
	private TableColumn<Order, String> dueDateCol;

	@FXML
	private TableColumn<Order, String> addressCol;

	@FXML
	private TableColumn<Order, Double> priceCol;

	@FXML
	private TableColumn<Order, String> statusCol;

	@FXML
	private Button cancelOrderBtn;

	@FXML
	private Button xBtn;

	@FXML
	private Button backBtn;

	private User user;

	CustomerMainScreenController cmsc = new CustomerMainScreenController();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	ObservableList<Order> myOrders;

	@FXML
	void backScreen(ActionEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
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
	}

	@FXML
	void exit(ActionEvent event) {
		ClientUI.chat.accept(new Message(ClientMessageType.EXIT, user.getUserName() + " 0")); // loggedin = 0
		System.exit(0);
	}

	@FXML
	void cancelOrder(ActionEvent event) throws IOException {

		Order selectedOrder = ordersTable.getSelectionModel().getSelectedItem();

		if (selectedOrder != null) {
			if (selectedOrder.getStatus().equals("Done")||selectedOrder.getStatus().equals("Pending for manager approvel")) {
				Alert infoAlert = new Alert(AlertType.INFORMATION, "Order Already Done!", ButtonType.OK);
				infoAlert.showAndWait();
				return;
			}
			Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to cancel this order?",
					ButtonType.YES, ButtonType.NO);
			Optional<ButtonType> r;
			r = alert.showAndWait();
			if (r.isPresent() && r.get() == ButtonType.YES) {
				ClientUI.chat.accept(new Message(ClientMessageType.UpadteOrderStatus, selectedOrder));
				Message message = (Message) ClientChat.returnedValueFromServer;
				if (message.getServerMessageType() == ServerMessageType.SUCCEED) {
					Alert infoAlert = new Alert(AlertType.INFORMATION, "Your request is waiting for manager approval",
							ButtonType.OK);
					infoAlert.showAndWait();
					selectedOrder.setStatus("Pending for manager approvel");
					ordersTable.refresh();

//					((Node) event.getSource()).getScene().getWindow().hide();
//					Stage primaryStage = new Stage();
//					FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CancelOrderScreen.fxml"));
//					Parent root = loader.load();
//					CancelOrderScreenController cos = loader.getController();
//					cos.setUser(user);
//					cos.setOrders();
//					Scene scene = new Scene(root);
//					primaryStage.setTitle("My Orders");
//					primaryStage.setScene(scene);
//					primaryStage.show();

				} else {
					Alert infoAlert = new Alert(AlertType.INFORMATION, "You cant cancel this order", ButtonType.OK);
					infoAlert.showAndWait();
				}
			}
			
		}
		else {
			
			Alert infoAlert = new Alert(AlertType.INFORMATION, "You have to choose order to cancel", ButtonType.OK);
			infoAlert.showAndWait();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		myOrders = FXCollections.observableArrayList();
		orderIdCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderNumber"));
		branchCol.setCellValueFactory(new PropertyValueFactory<Order, String>("branch"));
		orderDateCol.setCellValueFactory(new PropertyValueFactory<Order, String>("orderDate"));
		dueDateCol.setCellValueFactory(new PropertyValueFactory<Order, String>("deliveryDate"));
		addressCol.setCellValueFactory(new PropertyValueFactory<Order, String>("address"));
		orderInfoCol.setCellValueFactory(new PropertyValueFactory<Order, String>("orderDetails"));
		priceCol.setCellValueFactory(new PropertyValueFactory<Order, Double>("price"));
		statusCol.setCellValueFactory(new PropertyValueFactory<Order, String>("status"));
		ordersTable.setItems(myOrders);
	}

	public void setOrders() {
		ClientUI.chat.accept(new Message(ClientMessageType.GetCustomerOrders, user));
		Message message = (Message) ClientChat.returnedValueFromServer;

		if (message.getServerMessageType() == ServerMessageType.SUCCEED) {
			@SuppressWarnings("unchecked")
			ArrayList<Order> ordersArr = (ArrayList<Order>) message.getObj();
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					myOrders.addAll(ordersArr);
					ordersTable.refresh();
				}
			});
		}

	}

}
