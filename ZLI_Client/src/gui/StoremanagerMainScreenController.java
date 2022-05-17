package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class StoremanagerMainScreenController implements Initializable{
	private Parent fxml;
    @FXML
    private Button SmCreateAccountBTN;

    @FXML
    private Button SmLogoutBTN;

    @FXML
    private AnchorPane SmMainScreenWindowOpt;

    @FXML
    private Button SmOrdersForApprovalBTN;

    @FXML
    private Button SmUpdateCustomerBTN;

    @FXML
    private Button SmViewReportsBTN;

    @FXML
    private AnchorPane rootSmMainScreen;
    @FXML
    void CreateAccount(MouseEvent event) {
    	try {
    		fxml = FXMLLoader.load(getClass().getResource("StoremanagerCreateAccount.fxml"));
    		
    		SmMainScreenWindowOpt.getChildren().removeAll();
    		SmMainScreenWindowOpt.getChildren().setAll(fxml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void LogOutOPT(MouseEvent event) {

    }

    @FXML
    void OrdersForApprovalOPT(MouseEvent event) {
    	try {
    		fxml = FXMLLoader.load(getClass().getResource("StoremanagerOrderApproval.fxml"));
    		SmMainScreenWindowOpt.getChildren().removeAll();
    		SmMainScreenWindowOpt.getChildren().setAll(fxml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void UpdateCustomerStatusOPT(MouseEvent event) {

    }

    @FXML
    void ViewReportsOpt(MouseEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}


/**/