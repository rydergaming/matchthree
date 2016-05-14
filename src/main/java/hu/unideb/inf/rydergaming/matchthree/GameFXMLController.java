package hu.unideb.inf.rydergaming.matchthree;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class GameFXMLController implements Initializable {

    
    @FXML
    Label playerName;
    
    @FXML
    Canvas canvas; 

    Stage stage;
    Scene scene;
    
    Group root = new Group();
    
    GraphicsContext gc;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    }    
    
    public void initData(String nev) {
        playerName.setText(nev);
        gc = canvas.getGraphicsContext2D();
        
        Image img = new Image(this.getClass().getResourceAsStream("/sprites/waddup.jpg"));
        gc.drawImage(img, 0, 0);

        
    }
    
}
