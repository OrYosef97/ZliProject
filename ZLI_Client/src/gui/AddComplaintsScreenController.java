package gui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import client.ClientUI;
import common.Message;
import enumType.ClientMessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.User;

public class AddComplaintsScreenController {

    @FXML
    private Button backBtn;

    @FXML
    private Button xBtn;

    @FXML
    private TextArea complaintTextArea;

    @FXML
    private TextField cutomerNameField;

    @FXML
    private TextField idField;

    @FXML
    private DatePicker dateField;

    @FXML
    private Button confirmBtn;

    User user;
    @FXML
    void Confirm(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
		Optional<ButtonType> r = alert.showAndWait();
		if (r.isPresent() && r.get() == ButtonType.YES) {
			addComplaintToDB();
			Back(event); // go back to the previous screen
		}
		
    }

    @FXML
    void Back(ActionEvent event) {
    	try {
			((Node) event.getSource()).getScene().getWindow().hide();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CustomerServiceWorkerMainScreen.fxml"));
			Pane root = loader.load();
			// ClientMainScreenController clientMainScreenController =
			// loader.getController();
			// clientMainScreenController.setErrorTxtFVisability(false);
			Stage primaryStage = new Stage();
			Image icon = new Image("/gui/icon1.jpeg");
			primaryStage.getIcons().add(icon);
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("background.css").toExternalForm());
		    CustomerServiceWorkerMainScreenController csw = loader.getController();
		    csw.SetUser(user);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    

    @FXML
    void exit(ActionEvent event) {
    	
    	ClientUI.chat.accept(new Message(ClientMessageType.EXIT,user.getUserName())); //loggedin = 0
    	System.exit(0);
    }
    
    void addComplaintToDB () {
    	Integer CustomerID = Integer.parseInt(idField.getText().trim());
    	LocalDate Date=null;
    	Date = Date.now();
    	LocalDate endDate = Date.plusDays(3);
    	String Text = complaintTextArea.getText();
    	ArrayList<Object> data = new ArrayList<>();
    	data.add(CustomerID);
    	data.add(Date.toString());
    	data.add(endDate.toString());
    	data.add(Text);
    	ClientUI.chat.accept(new Message(ClientMessageType.AddComplaint,data));
    	
    }
    void SetUser(User user) {
		this.user = user;
	}

}
