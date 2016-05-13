package hu.unideb.inf.rydergaming.matchthree;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FXMLController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void startHandleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @FXML
    private void quitHandleButtonAction(ActionEvent event) {
    	System.exit(0);
    }
	@FXML 
	private void scoreHandleButtonAction(ActionEvent event) {
		
	}   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

 
}
