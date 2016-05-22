package hu.unideb.inf.rydergaming.matchthree.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class of the startScene.fxml.
 * @author Ryder
 *
 */
public class StartFXMLController implements Initializable {
    
	/**
	 * Label containing the game's title.
	 */
    @FXML
    private Label label;
    
    /**
     * Button starts the game.
     */
    @FXML
    private Button startGame;
    
    /**
     * TextField containing the player's name.
     */
    @FXML
    private TextField playerName;
    
    /**
     * Action that changes scene to change the scene.
     * @param event ActionEvent changes the scene.
     */
    @FXML
    private void startHandleButtonAction(ActionEvent event) {
        try {
            Stage stage;
            Parent root;

            stage = (Stage) startGame.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gameScene.fxml"));
            root = loader.load();            
                        
            loader.<GameFXMLController>getController().initData(playerName.getText());

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(GameFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    /**
     * Button that shuts down the game.
     * @param event ActionEvent that checks for mouseclicks.
     */    
    @FXML
    private void quitHandleButtonAction(ActionEvent event) {
    	System.exit(0);
    }
 
    /**
     * Auto generated initialize class for the controller.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

 
}
