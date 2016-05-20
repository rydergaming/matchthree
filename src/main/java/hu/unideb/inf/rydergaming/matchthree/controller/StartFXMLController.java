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

public class StartFXMLController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private Button start_game;
    @FXML
    private TextField playerName;
    
    String nev = "";
    
    @FXML
    private void startHandleButtonAction(ActionEvent event) {
        try {
            Stage stage;
            Parent root;
            //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
            stage = (Stage) start_game.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gameScene.fxml"));
            root = loader.load();
            nev = playerName.getText();
                        
            loader.<GameFXMLController>getController().initData(nev);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(GameFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    @FXML
    private void quitHandleButtonAction(ActionEvent event) {
    	System.exit(0);
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

 
}
