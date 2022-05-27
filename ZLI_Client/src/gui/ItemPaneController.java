package gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import logic.GeneralItem;

public class ItemPaneController {

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

	private CreateOrderScreenController cosC;// controller for create order screen
	private GeneralItem item;

	public GeneralItem getItem() {
		return item;
	}

	public void setItem(GeneralItem item,CreateOrderScreenController cosC ) {
		this.item = item;
		this.cosC=cosC;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				System.out.println(item.getImageUrl());
				ItemImageView.setImage(new Image(getClass().getResourceAsStream(item.getImageUrl())));
				nameLable.setText(item.getName());
				priceLable.setText(Double.toString(item.getPrice()));			
			}
		});
	}

	@FXML
	void showInPreviewScreen(MouseEvent event) {
		cosC.setSidePane(this);
	}

	public void setAmount(int amount) {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				amountLable.setText(Integer.toString(amount));
				
			}
		});
		
	}

}
