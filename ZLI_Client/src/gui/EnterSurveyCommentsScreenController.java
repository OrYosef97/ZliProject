package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import client.ClientUI;
import common.Message;
import enumType.ClientMessageType;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import logic.User;

public class EnterSurveyCommentsScreenController implements Initializable {
	@FXML
    private Button xBtn;

    @FXML
    private Label suveryLabel;

    @FXML
    private Label customerIDLabel;

    @FXML
    private Label answersLabel;
    
    @FXML
    private Label fillLabel;
    @FXML
    private Line line;
    
	@FXML
	private TextField surveyTypeText;
	@FXML
	private TextField surveyIDText;

	@FXML
	private TextField customerIDText;

	@FXML
	private ChoiceBox<Integer> AnswerA, AnswerB, AnswerC, AnswerD, AnswerE, AnswerF;

	Integer[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

	User user;
	
	Integer commentsCounter;

	@FXML
	void Back(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/WorkerMainScreen.fxml"));
			Pane root;
			root = loader.load();
			Stage primaryStage = new Stage();
			Image icon = new Image("/gui/icon1.jpeg");
			primaryStage.getIcons().add(icon);
			Scene scene = new Scene(root);
			WorkerMainScreenController wc = loader.getController();
			wc.SetUser(user);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
    void Save(ActionEvent event) {
    	Integer surveyID,customerID,ans1,ans2,ans3,ans4,ans5,ans6;
    	String surveyType,userName;
    	//commentsCounter++; //another customer answered.
    	surveyID = Integer.parseInt(surveyIDText.getText());    	
    	customerID = Integer.parseInt(customerIDText.getText());
    	surveyType = surveyTypeText.getText();
    	userName = user.getUserName();
    	ans1 = AnswerA.getValue();
    	ans2 = AnswerB.getValue();
    	ans3 = AnswerC.getValue();
    	ans4 = AnswerD.getValue();
    	ans5 = AnswerE.getValue();
    	ans6 = AnswerF.getValue();
    	ArrayList<Object> data = new ArrayList<>();
    	data.add(surveyID);
    	data.add(customerID);
    	data.add(surveyType);
    	data.add(userName);
    	data.add(ans1);
    	data.add(ans2);
    	data.add(ans3);
    	data.add(ans4);
    	data.add(ans5);
    	data.add(ans6);
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
		Optional<ButtonType> r = alert.showAndWait();
		if (r.isPresent() && r.get() == ButtonType.YES) {
			ClientUI.chat.accept(new Message(ClientMessageType.UpdateSurveyComment, data));			
		}
    }

	@FXML
	void exit(ActionEvent event) {
		ClientUI.chat.accept(new Message(ClientMessageType.EXIT, user.getUserName())); // loggedin = 0
		System.exit(0);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		AnswerA.getItems().addAll(nums);
		AnswerB.getItems().addAll(nums);
		AnswerC.getItems().addAll(nums);
		AnswerD.getItems().addAll(nums);
		AnswerE.getItems().addAll(nums);
		AnswerF.getItems().addAll(nums);

	}
	
	  @FXML
	    void Enter(ActionEvent event) {
		  line.setVisible(true);
		  customerIDText.setVisible(true);
		  surveyTypeText.setVisible(true);
		  fillLabel.setVisible(true);
		  suveryLabel.setVisible(true);
		  customerIDLabel.setVisible(true);
		  answersLabel.setVisible(true);
		  AnswerA.setVisible(true);
		  AnswerB.setVisible(true);
		  AnswerC.setVisible(true);
		  AnswerD.setVisible(true);
		  AnswerE.setVisible(true);
		  AnswerF.setVisible(true);
		  surveyIDText.setDisable(true);
		  /*Checks and if does not exist, creates new surveyResults in DB*/
		  ClientUI.chat.accept(new Message(ClientMessageType.CheckOrCreateSurveyResults,(Object)surveyIDText.getText()));
	    }

	public void SetUser(User user) {
		this.user = user;
	}
}
