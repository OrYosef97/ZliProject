package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import client.ClientChat;
import client.ClientUI;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.Order;

public class OrdersScreenController implements Initializable {

	@FXML
	private Button xBtn;

	@FXML
	private TableView<Order> OrdersTable;

	@FXML
	private TableColumn<Order, Integer> OrderNumColum;

	@FXML
	private TableColumn<Order, String> BranchColum;

	@FXML
	private TableColumn<Order, String> DeliveryDateColum;

	@FXML
	private TableColumn<Order, Integer> PriceColum;

	ObservableList<Order> orders;

	@FXML
	void BackBtn(ActionEvent event) {
		try {

			((Node) event.getSource()).getScene().getWindow().hide();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/DeliveryPersonScreen.fxml"));
			Pane root = loader.load();
			// ClientMainScreenController clientMainScreenController =
			// loader.getController();
			// clientMainScreenController.setErrorTxtFVisability(false);
			DeliveryPersonScreenController sc = loader.getController();
			sc.setRoleName("de"); // needs to be general.
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
		System.exit(0);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		orders = FXCollections.observableArrayList();
		OrderNumColum.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderNumber"));
		PriceColum.setCellValueFactory(new PropertyValueFactory<Order, Integer>("price"));
		// orderDetailsColum.setCellValueFactory(new PropertyValueFactory<Order,
		// String>("Details"));
		BranchColum.setCellValueFactory(new PropertyValueFactory<Order, String>("Shop"));
		DeliveryDateColum.setCellValueFactory(new PropertyValueFactory<Order, String>("orderDate"));
		OrdersTable.setItems(orders);

	}

	public void loadOrders() {
		ClientUI.chat.accept("getOrders");
		ArrayList<Order> ordersArray = (ArrayList<Order>) ClientChat.returnedValueFromServer;
		System.out.println("got it");
		System.out.println("got " + ordersArray);// just for check
		if (ordersArray == null)
			return;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				orders.addAll(ordersArray);
				System.out.println("update " + orders);// just for check
				OrdersTable.refresh();
			}
		});
	}

	/**
	 * @param event
	 */
	@FXML
	void Confirm(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
		Optional<ButtonType> r = alert.showAndWait();
		if (r.isPresent() && r.get() == ButtonType.YES) {
			Order selectedOrder = OrdersTable.getFocusModel().getFocusedItem();
			ClientUI.chat.accept("UpdateOrderDelivered " + selectedOrder.getOrderNumber());
			OrdersTable.getItems().removeAll(OrdersTable.getSelectionModel().getSelectedItems());
			OrdersTable.refresh();
		}
	}

}
