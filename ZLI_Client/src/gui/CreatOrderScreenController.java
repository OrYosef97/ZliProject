package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import client.ClientChat;
import client.ClientUI;
import common.Message;
import enumType.ClientMessageType;
import enumType.ServerMessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import logic.Item;

public class CreatOrderScreenController implements Initializable {

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

    }

    @FXML
    void exit(ActionEvent event) {
    	System.out.println();
    	ClientUI.chat.accept(new Message(ClientMessageType.EXIT,userName+" 0")); //loggedin = 0
    	System.exit(0);
    }

    @FXML
    void openCartScreen(ActionEvent event) {

    }

    @FXML
    void openItemCatalog(ActionEvent event) {//Default to fill later 

    }

    @FXML
    void openProductCatalog(ActionEvent event) {

    }

    @FXML
    void removeFromCart(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientUI.chat.accept(new Message(ClientMessageType.GetItems,null));
		Message message = (Message) ClientChat.returnedValueFromServer;
		
		if(message.getServerMessageType()==ServerMessageType.SUCCEED) {
			//need to load the data here
		}
		
		
		
	}

}
