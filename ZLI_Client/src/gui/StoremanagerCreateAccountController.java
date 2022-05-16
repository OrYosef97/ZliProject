package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


import client.ClientChat;
import client.ClientUI;
import common.Message;
import enumType.ClientMessageType;
import enumType.ServerMessageType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import logic.CustomerDetails;

public class StoremanagerCreateAccountController implements Initializable {

	@FXML
	private Button AccCreateSearchIDBTN;

	@FXML
	private Button AccCreationBackBTN;

	@FXML
	private Button AccCreationCreateBTN;

	@FXML
	private TableView<CustomerDetails> customerDetailsTable;

	@FXML
	private TableColumn<CustomerDetails, String> fNameCol;

	@FXML
	private TableColumn<CustomerDetails, String> lNameCol;

	@FXML
	private TableColumn<CustomerDetails, String> idCol;

	@FXML
	private TableColumn<CustomerDetails, String> creditCardCol;

	@FXML
	private TableColumn<CustomerDetails, String> phoneNumberCol;

	@FXML
	private TableColumn<CustomerDetails, String> emailAddressCol;

	@FXML
	private TableColumn<CustomerDetails, String> statusCol;

	@FXML
	private TextField idNumFLD;

	@FXML
	private AnchorPane rootSmAccoutCreation;

	String query = null;
	Connection connection = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	CustomerDetails customerDetails = null;

	ObservableList<CustomerDetails> customerDetailList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		idCol.setCellValueFactory(new PropertyValueFactory<>("custmerID"));
		creditCardCol.setCellValueFactory(new PropertyValueFactory<>("creditCard"));
		phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		emailAddressCol.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
		statusCol.setCellValueFactory(new PropertyValueFactory<>("customerStatus"));
		
		customerDetailsTable.setItems(customerDetailList);
		
		try {
			loadData();
		} catch (IOException e) {
			System.out.println("cant load table");
			e.printStackTrace();
		}

	}

	@FXML
	void BackClicked(MouseEvent event) {

	}

	@FXML
	void CreateClicked(MouseEvent event) {

	}

	@FXML
	void SearchID() {

	}

	private void loadData() throws IOException {

		try {
			ClientUI.chat.accept(new Message(ClientMessageType.GetCustomerDetails,null));
			Message message = (Message) ClientChat.returnedValueFromServer;
			if(message.getServerMessageType() == ServerMessageType.FAILED)
			{
				return;
			}
			//can add here an error to screen
			ArrayList<CustomerDetails> cd = (ArrayList<CustomerDetails>)message.getObj();
			customerDetailList.addAll(cd);
			customerDetailsTable.refresh();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
