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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import logic.Item;

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
    
    private ItemPaneController presentedItemController;//
    
    List<Item> itemsArray = new ArrayList<Item>();
    
    
    String userName;
    
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    

    @FXML
    void addToCart(ActionEvent event) {
    	Item item = presentedItemController.getItem();
    	int i = itemsArray.indexOf(presentedItemController.getItem());//get item index
    	if(!itemsArray.contains(item)) {
    		itemsArray.add(item);
    		i = itemsArray.indexOf(presentedItemController.getItem());
    	}
    	itemsArray.get(i).addOneItem();
    	amountLable.setText(itemsArray.get(i).getAmount().toString());
    }

    @FXML
    void exit(ActionEvent event) {
    	System.out.println();
    	Message msg = new Message(ClientMessageType.EXIT,userName+" 0");
    	ClientUI.chat.accept(msg); //loggedin = 0
    	System.exit(0);
    }

    @FXML
    void openCartScreen(ActionEvent event) {

    }

    @FXML
    void openItemCatalog(ActionEvent event) {//Default to fill later 

    }

    @FXML
    void openProductCatalog(ActionEvent event) {//load a new gridpane

    }

    @FXML
    void removeFromCart(ActionEvent event) {
    	Item item = presentedItemController.getItem();
    	int i = itemsArray.indexOf(item);//get item index
    	if(itemsArray.contains(item))
    		itemsArray.get(i).removeOneItem();
    }
    
    public void setSidePane(ItemPaneController ipc) {
    	presentedItemController = ipc;
    	Item  item = ipc.getItem();
    	Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				nameLable.setText(item.getName());
				priceLable.setText(Double.toString(item.getPrice()));
				amountLable.setText(Integer.toString(item.getAmount()));
				sideImageView.setImage(new Image(item.getImageUrl()));
				
			}
		});
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		ClientUI.chat.accept(new Message(ClientMessageType.GetItems,null));
//		Message message = (Message) ClientChat.returnedValueFromServer;
//		
//		if(message.getServerMessageType()==ServerMessageType.SUCCEED) {
//			//need to load the data here
//		}
//		
		//Image image = new Image("/images.img/bb.jpeg");
		Double price = 15.0;
		Item item = new Item("052", "Rose", "Flower", price, "/images.img/flowers.PNG");
		int row =0;
		int col=0;
		try {
		for(int i=0;i<15;i++) {	
			itemsArray.add(item);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ItemPane.fxml"));
				AnchorPane anc = loader.load();
			ItemPaneController ipc = loader.getController();
			ipc.setItem(item);
			if(col==3) {
				row++;
				col=0;
			}
			grid.add(anc, col, row);
			col++;
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
