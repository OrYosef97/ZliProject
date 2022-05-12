package gui;

import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.Order;

public class ViewOrdersScreenController implements Initializable {

	@FXML
	private TableColumn<Order, String> ColorColum;

	@FXML
	private TableColumn<Order, String> DeliveryDateColum;

	@FXML
	private TableColumn<Order, String> GreatingCardColum;

	@FXML
	private TableColumn<Order, String> OrderDateColum;

	@FXML
	private TableColumn<Order, String> OrderNumColum;

	@FXML
	private TableColumn<Order, Integer> PriceColum;

	@FXML
	private TableColumn<Order, String> ShopColum;

	@FXML
	private Button backBtn;

	@FXML
	private TableColumn<Order, String> orderDetailsColum;

	@FXML
	private TableView<Order> ordersTableView;

	@FXML
	private Button xBtn;

	private ObservableList<Order> orders;

	@FXML
	void back(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ClientMainScreen.fxml"));
			Pane root = loader.load();
			Stage primaryStage = new Stage();
			Image icon = new Image("/gui/icon1.jpeg");
			primaryStage.getIcons().add(icon);
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void exit(ActionEvent event) {
		ClientUI.chat.accept("disconnect");
		System.exit(0);
	}

	
	public void loadOrders() {
		ClientUI.chat.accept("getOrders");
		ArrayList<Order> ordersArray=  (ArrayList<Order>)ClientChat.returnedValueFromServer;
		System.out.println("got it");
		System.out.println("got "+ordersArray);//just for check
		if (ordersArray==null)
			return;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				orders.addAll(ordersArray);
				System.out.println("update "+orders);//just for check
				ordersTableView.refresh();	
				}
			});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		orders =FXCollections.observableArrayList();
		OrderNumColum.setCellValueFactory(new PropertyValueFactory<Order, String>("orderNumber"));
		PriceColum.setCellValueFactory(new PropertyValueFactory<Order, Integer>("price"));
		GreatingCardColum.setCellValueFactory(new PropertyValueFactory<Order, String>("greetingCard"));
		ColorColum.setCellValueFactory(new PropertyValueFactory<Order, String>("color"));
		orderDetailsColum.setCellValueFactory(new PropertyValueFactory<Order, String>("dOrder"));
		ShopColum.setCellValueFactory(new PropertyValueFactory<Order, String>("shop"));
		DeliveryDateColum.setCellValueFactory(new PropertyValueFactory<Order, String>("date"));
		OrderDateColum.setCellValueFactory(new PropertyValueFactory<Order, String>("orderDate"));
		ordersTableView.setItems(orders);
		
	}

	public String fixDate(String str) {
		String date[] = str.split("-");
		return date[2] + "." + date[1] + "." + date[0];

	}

}

/*System.out.println("new "+message);
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				String ordersDetails[] = message.split("//z");
				if (ordersDetails.length < 10)
					return;
				Order order;
				Integer price = 0;
				int i = 0;
				while (ordersDetails[i]!= "end") {
					price = Integer.parseInt(ordersDetails[i + 1]);
					order = new Order(ordersDetails[i], price, ordersDetails[i + 2], ordersDetails[i + 3], ordersDetails[i + 4],
							ordersDetails[i + 5], fixDate(ordersDetails[i + 6]), fixDate(ordersDetails[i + 8]));
					i += 10;
					System.out.println(order);
					orders.add(order);
				}
				
			}
		});
		 ordersTableView.refresh();*/
