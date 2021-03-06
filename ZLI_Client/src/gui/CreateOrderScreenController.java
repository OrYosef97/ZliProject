package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import client.ClientChat;
import client.ClientUI;
import common.Message;
import enumType.AccountStatus;
import enumType.ClientMessageType;
import enumType.ServerMessageType;
import javafx.application.Platform;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import logic.Customer;
import logic.GeneralItem;
import logic.Item;
import logic.Product;
import logic.User;

public class CreateOrderScreenController implements Initializable {

	@FXML
	private Button addBtn;

	@FXML
	private Label amountLable;

	@FXML
	private Button continueToCartBtn;

	@FXML
	private GridPane grid;

	@FXML
	private Button itemCatalogBtn1;

	@FXML
	private Label nameLable;

	@FXML
	private Label priceLable;

	@FXML
	private Button productCatalogBtn2;

	@FXML
	private Button removeBtn;

	@FXML
	private ScrollPane scroll;

	@FXML
	private ImageView sideImageView;

	@FXML
	private Button xBtn;

	@FXML
	private Button backBtn;

	public static CreateOrderScreenController createOrderSC;

	private ItemPaneController presentedItemController;//

	ArrayList<Item> itemsArray = new ArrayList<Item>();
	ArrayList<Product> productArray = new ArrayList<Product>();

	private User user;

	public User getUserName() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@FXML
	void Back(ActionEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CustomerMainScreen1.fxml"));
		Parent root = loader.load();
		CustomerMainScreenController cmSC = loader.getController();
		cmSC.setUser(user);
		Scene scene = new Scene(root);

		primaryStage.setTitle("Customer Main Window");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	@FXML
	void addToCart(ActionEvent event) {
		int i;
		GeneralItem itemOrProduct = presentedItemController.getItem();
		if (itemOrProduct instanceof Product) {
			Product product = (Product) itemOrProduct;
			if (!productArray.contains(product)) {
				productArray.add(product);
			}
			i = productArray.indexOf(product);
			Integer amount = productArray.get(i).getAmount();
			productArray.get(i).setAmount(++amount);
		} else {// its an Item
			Item item = (Item) itemOrProduct;
			if (!itemsArray.contains(item))
				itemsArray.add(item);
			i = itemsArray.indexOf(item);
			Integer amount = itemsArray.get(i).getAmount();
			itemsArray.get(i).setAmount(++amount);
		}
		amountLable.setText(itemOrProduct.getAmount().toString());
	}

	@FXML
	void removeFromCart(ActionEvent event) {
		int i;
		GeneralItem itemOrProduct = presentedItemController.getItem();
		if (itemOrProduct instanceof Product) {
			Product product = (Product) itemOrProduct;
			i = productArray.indexOf(product);
			if (i == -1)
				return;// not exist in array
			int amount = productArray.get(i).getAmount();
			if (amount > 0) {				
				productArray.get(i).setAmount(--amount);
				presentedItemController.setAmount(amount);//need to send Integer
			}
		} else {
			Item item = (Item) itemOrProduct;
			i = itemsArray.indexOf(item);
			if (i == -1)
				return;// not exist in array
			int amount = itemsArray.get(i).getAmount();
			if (amount > 0)
				itemsArray.get(i).setAmount(--amount);
		}
		amountLable.setText(itemOrProduct.getAmount().toString());
	}

	@FXML
	void exit(ActionEvent event) {
		ClientUI.chat.accept(new Message(ClientMessageType.EXIT, user.getUserName() + " 0")); // loggedin = 0
		System.exit(0);
	}

	@FXML
	void openCartScreen(ActionEvent event) throws IOException {
		ClientUI.chat.accept(new Message(ClientMessageType.GetCustomer, user.getUserName()));
		Message message = (Message) ClientChat.returnedValueFromServer;
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("You Don't Have Permeation");// Permeation
		if (message.getServerMessageType() == ServerMessageType.FAILED) {
			alert.setContentText("Contact your branch manager to arrange you'r user card, Thanks!");
			alert.showAndWait();
		} else {
			Customer customer = (Customer) message.getObj();
			if (customer.getAccountStatus() == AccountStatus.UNCONFIRMED) {
				alert.setContentText("Contact your branch manager to arrange payment, Thanks!");
				alert.showAndWait();
			}
			if (customer.getAccountStatus() == AccountStatus.CONFIRMED) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/OrderCompletionScreen.fxml"));
				Parent root = loader.load();
				OrderCompletionScreenController ocsc = loader.getController();
				// need to pass the order to the next controller
				Scene scene = new Scene(root);
				Stage primaryStage = new Stage();
				primaryStage.setTitle("Order completion Screen");
				primaryStage.setScene(scene);
				primaryStage.show();
			} else {
				alert.setContentText("Your acount is Frozen! Please contact your branch manager for more info!");
				alert.showAndWait();
			}
		}
	}

	@FXML
	void openItemCatalog(ActionEvent event) {// Default to fill later
		ClientUI.chat.accept(new Message(ClientMessageType.GetItems, "items"));
		Message message = (Message) ClientChat.returnedValueFromServer;
		itemsArray = (ArrayList<Item>) message.getObj();
		try {
			setGrid(itemsArray);
		} catch (IOException e) {
			System.out.println("Can't load items");
			e.printStackTrace();
		}
	}

	@FXML
	void openProductCatalog(ActionEvent event) {// load a new gridpane
		ClientUI.chat.accept(new Message(ClientMessageType.GetProducts, "products"));
		Message message = (Message) ClientChat.returnedValueFromServer;
		productArray = (ArrayList<Product>) message.getObj();
		try {
			setGrid(productArray);
		} catch (IOException e) {
			System.out.println("Can't load Products");
			e.printStackTrace();
		}
	}

	public void setSidePane(ItemPaneController ipc) {
		presentedItemController = ipc;
		GeneralItem item = ipc.getItem();
		Platform.runLater(new Runnable() {

			@Override
			public void run() {// mayby dont needed here
				nameLable.setText(item.getName());
				priceLable.setText(Double.toString(item.getPrice()));
				amountLable.setText(Integer.toString(item.getAmount()));
				sideImageView.setImage(new Image(item.getImageUrl()));

			}
		});
	}

	// @SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientUI.chat.accept(new Message(ClientMessageType.GetItems, "items"));
		Message message = (Message) ClientChat.returnedValueFromServer;
		itemsArray = (ArrayList<Item>) message.getObj();
		try {
			setGrid(itemsArray);
		} catch (IOException e) {
			System.out.println("Can't load items");
			e.printStackTrace();
		}
		
	}


	public void setGrid(ArrayList<?extends GeneralItem> items) throws IOException {
		grid.getChildren().clear();
		int row = 1, col = 0;
		for (GeneralItem item : items) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ItemPane.fxml"));
			AnchorPane anc = loader.load();
			ItemPaneController ipc = loader.getController();
			ipc.setItem(item, this);
			if (col == 3) {
				row++;
				col = 0;
			}
			grid.add(anc, col, row);
			col++;
		}
	}

}
