package gui;

import java.time.LocalDate;

import client.ClientChat;
import client.ClientUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.Order;

public class OrderScreenController {

	@FXML
	private TextArea OrderDetailsTxtArea;

	@FXML
	private TextField OrderNUmTxtField;

	@FXML
	private Button backBtn;

	@FXML
	private TextField colortxtField;

	@FXML
	private DatePicker deliveryDateDP;

	@FXML
	private Label errorLable;

	@FXML
	private TextArea greetingCardTxtArea;

	@FXML
	private TextField orderDateTxtField1;

	@FXML
	private Label orderDetailsLbl;

	@FXML
	private TextField priceTxtField;

	@FXML
	private Button saveBtn;

	@FXML
	private TextField shopTxtField;

	private String orderColor, deliveryDate;

	@FXML
	public void backToMainScreen(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ClientMainScreen.fxml"));
			Pane root = loader.load();
			// ClientMainScreenController clientMainScreenController =
			// loader.getController();
			// clientMainScreenController.setErrorTxtFVisability(false);
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
	public void save(ActionEvent event) {

		String saved = "failed";
		String newColor = colortxtField.getText();
		String newDate = deliveryDateDP.getValue().toString();
		//System.out.println(newDate);

		if (orderColor.equals(newColor)&& deliveryDate.equals(newDate)) {
			errorLable.setText("You must change something in order to save updates");
			errorLable.setVisible(true);
		} else {
			if (!orderColor.equals(newColor)) {
				ClientUI.chat.accept(String.format("updateColor %s %s", newColor, OrderNUmTxtField.getText()));
				saved = (String) ClientChat.returnedValueFromServer;
				orderColor=newColor;
			}
			if (!deliveryDate.equals(newDate)) {
				ClientUI.chat.accept(String.format("updateDate %s %s", newDate, OrderNUmTxtField.getText()));
				saved = (String) ClientChat.returnedValueFromServer;
				deliveryDate=newDate;
			}
			if (saved.equals("failed")) {
				errorLable.setText("Could'nt Save Updates");
				errorLable.setVisible(true);
			} else {
				errorLable.setVisible(false);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("saved");
				alert.setHeaderText("Changes saved succesfully");
				alert.showAndWait();
			}
		}

	}

	public void loadOrder(Order o) {
		// orderNumber+" "+price+" "+greetingCard+" "+color+" "+dOrder+" "+shop+"
		// "+date+" "+orderDate;
		errorLable.setVisible(false);
		System.out.println("loding order to the screen");//to remove
		String orderDetails[] = o.toString().split("//z");
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				OrderNUmTxtField.setText(orderDetails[0]);
				OrderNUmTxtField.setEditable(false);
				priceTxtField.setText(orderDetails[1]);
				priceTxtField.setEditable(false);
				greetingCardTxtArea.setText(orderDetails[2]);
				greetingCardTxtArea.setEditable(false);
				colortxtField.setText(orderDetails[3]);
				orderColor = orderDetails[3];
				// here need to check if the OrderNum is for self made if so make
				// OrderDetailsTxtArea
				OrderDetailsTxtArea.setEditable(false);
				if (orderDetails[0].contains("so")) {//
					OrderDetailsTxtArea.setText(orderDetails[4]);
					orderDetailsLbl.setVisible(true);// new
					OrderDetailsTxtArea.setVisible(true);
				}else {
				orderDetailsLbl.setVisible(false);// new
				OrderDetailsTxtArea.setVisible(false);/// new
				}
				shopTxtField.setText(orderDetails[5]);
				shopTxtField.setEditable(false);
				String dateSplit[]= orderDetails[6].split(" ");
				deliveryDate = dateSplit[0];//To save the original date
				dateSplit = dateSplit[0].split("-");
				deliveryDateDP.setValue(LocalDate.of(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]), Integer.parseInt(dateSplit[2])));
				dateSplit = orderDetails[7].split(" ");
				dateSplit = dateSplit[0].split("-");
				orderDateTxtField1.setText(dateSplit[2]+"."+dateSplit[1]+"."+dateSplit[0]);
				orderDateTxtField1.setEditable(false);

			}

		});

	}
}
