package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DBConnector.GeneralConnector;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.Client;
import ocsf.server.ConnectionToClient;
import server.ServerUI;

public class ServerScreenController implements Initializable {

	@FXML
	private Button connectBtn;

	@FXML
	private TableView<Client> connectedClientsTable;

	@FXML
	private TextArea consoleTxtArea;

	@FXML
	private TextField dbNameTxtField;

	@FXML
	private PasswordField dbPasswordTxtField;

	@FXML
	private TextField dbUserTxtField;

	@FXML
	private Button disconnectBtn;

	@FXML
	private TextField ipTextField;

	@FXML
	private TextField portTxtField;

	@FXML
	private TableColumn<Client, String> hostColum;

	@FXML
	private TableColumn<Client, String> ipColum;

	@FXML
	private TableColumn<Client, String> statusColum;

	private ObservableList<Client> clients = FXCollections.observableArrayList();

	@FXML
	void connectToServer(ActionEvent event) throws SQLException {
		GeneralConnector.connectToDB(dbNameTxtField.getText().trim(), dbUserTxtField.getText().trim(),
				dbPasswordTxtField.getText().trim(), this);
		ServerUI.runServer("5555");
		setTextToConsole("Server listening for connections on port ");
		connectBtn.setDisable(true);

	}

	public void setTextToConsole(String msg) {
		consoleTxtArea.appendText("\n" + msg);
	}

	@FXML
	void disconnectFromServer(ActionEvent event) throws SQLException {
		GeneralConnector.CloseConnection();// maybe need to send connection as param
		System.out.println("Server Disconnected");
		System.exit(0);
	}

	public void addClient(ConnectionToClient client) {

		String clientInfo[] = client.toString().split(" ");
		Client c = new Client(clientInfo[1], clientInfo[0], "Connected");
		clients.add(c);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
					connectedClientsTable.refresh();// maybe needed here
			}
		});

	}

	public void delClient(ConnectionToClient client, int numOfclients) {
		String clientInfo[] = client.toString().split(" ");
		Client c = new Client(clientInfo[1], clientInfo[0], "Connected");
		// Client cNewStatus = new Client(clientInfo[1],clientInfo[0],"Disconnected");
		clients.remove(c);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				System.out.println("got here");
				// clients.add(cNewStatus);
				connectedClientsTable.refresh();// maybe needed here
			}
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		connectBtn.setDisable(false);
		consoleTxtArea.setEditable(false);
		hostColum.setCellValueFactory(new PropertyValueFactory<Client, String>("host"));
		ipColum.setCellValueFactory(new PropertyValueFactory<Client, String>("ip"));
		statusColum.setCellValueFactory(new PropertyValueFactory<Client, String>("status"));
		connectedClientsTable.setItems(clients);// maybe not needed here
	}

}