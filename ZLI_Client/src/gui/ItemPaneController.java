package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import logic.Item;

public class ItemPaneController implements Initializable {

    @FXML
    private ImageView ItemImageView;

    @FXML
    private AnchorPane ItemPane;

    @FXML
    private Label amountLable;

    @FXML
    private Label nameLable;

    @FXML
    private Label priceLable;

    @FXML
    private Button removeBtn;
    
    private CreateOrderScreenController cosSC;//controller for create order screen
    private Item item;
    
    public Item getItem() {
		return item;
	}
    
    public void add() {
    	Platform.runLater(new Runnable() {

			@Override
			public void run() {
				int amount = item.getAmount();
				item.setAmount(++amount);
				amountLable.setText(Integer.toString(amount));
			}
    		
    	});
    }
    public void remove() {
    	Platform.runLater(new Runnable() {

			@Override
			public void run() {
				int amount = item.getAmount();
				if(amount<=0)return;
				item.setAmount(--amount);
				amountLable.setText(Integer.toString(amount));
			}
    		
    	});
    }
    
    


	public void setItem(Item item){
    	this.item = item;
    	Platform.runLater(new Runnable() {

			@Override
			public void run() {
				ItemImageView.setImage(item.getImage());
				nameLable.setText(item.getName());
				priceLable.setText(Double.toString(item.getPrice()));
				
			}
    		
    	});
    }
    

	@FXML
    void addItem(ActionEvent event) {
		int amount = item.getAmount();
		item.setAmount(++amount);
		amountLable.setText(Integer.toString(amount));
    }

    @FXML
    void removeItem(ActionEvent event) {
    	int amount = item.getAmount();
    	if(amount>=0) {
    		item.setAmount(--amount);;
    		amountLable.setText(Integer.toString(amount));
    	}

    }

    @FXML
    void showInPreviewScreen(MouseEvent event) {
    	cosSC.setSidePane(this);
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		amountLable.setText("0");
		
	}

}
