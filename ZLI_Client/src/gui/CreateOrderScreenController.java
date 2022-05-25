package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import client.ClientUI;
import common.Message;
import enumType.ClientMessageType;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
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
			if (amount > 0)
				productArray.get(i).setAmount(--amount);
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
    	ClientUI.chat.accept(new Message(ClientMessageType.EXIT,user.getUserName()+" 0")); //loggedin = 0
    	System.exit(0);
	}

	@FXML
	void openCartScreen(ActionEvent event) {
		ClientUI.chat.accept(new Message(ClientMessageType.GetCustomer, user.getUserName()));

	}

	@FXML
	void openItemCatalog(ActionEvent event) {// Default to fill later

	}

	@FXML
	void openProductCatalog(ActionEvent event) {// load a new gridpane

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Double price = 15.0;
		Item item = new Item("052", "Rose", "Flower", price, "/images.img/bb.jpeg");
		int row = 1;
		int col = 0;
		try {
			for (int i = 0; i < 15; i++) {
				itemsArray.add(item);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ItemPane.fxml"));
				AnchorPane anc = loader.load();
				ItemPaneController ipc = loader.getController();
				ipc.setItem(item,this);
				if (col == 3) {
					row++;
					col = 0;
				}
				grid.add(anc, col, row);
				col++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setGrid(ArrayList<GeneralItem> items) throws IOException {
		grid.getChildren().clear();
		int row = 1, col = 0;
		for (GeneralItem item : items) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ItemPane.fxml"));
			AnchorPane anc = loader.load();
			ItemPaneController ipc = loader.getController();
			ipc.setItem(item,this);
			if (col == 3) {
				row++;
				col = 0;
			}
			grid.add(anc, col, row);
			col++;
		}
	}

}
