package gui;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import client.ClientChat;
import client.ClientUI;
import common.Message;
import enumType.ClientMessageType;
import enumType.ServerMessageType;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import logic.Survey;
import logic.User;

public class SurveryConclusionsScreenController implements Initializable {

	@FXML
	private TableView<Survey> SurveyTable;

	@FXML
	private TableColumn<Survey, Integer> SurveryIDColumn;

	@FXML
	private TableColumn<Survey, String> SurveyTypeColumn;

	@FXML
	private TableColumn<Survey, String> SurveyUploaderColumn;

	@FXML
	private Button xBtn;

	@FXML
	private TextField fileNameTextField;

	User user;

	ObservableList<Survey> surveys;
	
	File conclusionFile;

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
			CustomerServiceWorkerMainScreenController csw = loader.getController();
			csw.SetUser(user);
			Image icon = new Image("/gui/icon1.jpeg");
			primaryStage.getIcons().add(icon);
			Scene scene = new Scene(root);
			// scene.getStylesheets().add(getClass().getResource("background.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void Browse(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("PDF Files","*.pdf"));
		conclusionFile = fc.showOpenDialog(null);
		fileNameTextField.setText(conclusionFile.getName());
		;
	}

	@FXML
	void UploadConclusions(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
		Optional<ButtonType> r = alert.showAndWait();
		if (r.isPresent() && r.get() == ButtonType.YES) {
			Survey selectedSurvey = SurveyTable.getFocusModel().getFocusedItem();
			ArrayList<Object> data = new ArrayList<>();
			data.add(selectedSurvey);
			data.add(conclusionFile);
			ClientUI.chat.accept(new Message(ClientMessageType.AddConclusion, data));
			// SuveryTable.getItems().removeAll(OrdersTable.getSelectionModel().getSelectedItems());
			SurveyTable.refresh();
		
		}
	}

	@FXML
	void exit(ActionEvent event) {
		ClientUI.chat.accept(new Message(ClientMessageType.EXIT,user.getUserName())); //loggedin = 0
    	System.exit(0);
	}
	

	void SetUser(User user) {
		this.user = user;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		surveys = FXCollections.observableArrayList();
		SurveryIDColumn.setCellValueFactory(new PropertyValueFactory<Survey, Integer>("surveyID"));
		SurveyTypeColumn.setCellValueFactory(new PropertyValueFactory<Survey, String>("surveyType"));
		SurveyUploaderColumn.setCellValueFactory(new PropertyValueFactory<Survey, String>("surveyUpdater"));
		SurveyTable.setItems(surveys);
		loadSurvey();

	}

	private void loadSurvey() {
		ClientUI.chat.accept(new Message(ClientMessageType.GetSurveys, ""));
		Message message = (Message) ClientChat.returnedValueFromServer;

		ArrayList<Survey> surveysArray = (ArrayList<Survey>) message.getObj();
		System.out.println("got it");
		System.out.println("got " + surveysArray);// just for check
		if (message.getServerMessageType() == ServerMessageType.FAILED)
			return;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				surveys.addAll(surveysArray);

				System.out.println("update " + surveys);// just for check
				SurveyTable.refresh();
			}
		});

	}

}
