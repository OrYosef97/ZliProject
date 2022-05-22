package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

  
    private CreateOrderScreenController cosC;//controller for create order screen
    private Item item;
    
    public Item getItem() {
		return item;
	}

	public void setItem(Item item){
    	this.item = item;
    	Platform.runLater(new Runnable() {

			@Override
			public void run() {
				ItemImageView.setImage(new Image(item.getImageUrl()));
				nameLable.setText(item.getName());
				priceLable.setText(Double.toString(item.getPrice()));
				
			}
    		
    	});
    }
    

    @FXML
    void showInPreviewScreen(MouseEvent event) {
    	cosC.setSidePane(this);
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		amountLable.setText("0");
		
	}

}
