package hu.unideb.inf.rydergaming.matchthree;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
    boolean picked = false;
    
    int sX, sY, tX, tY;
    
    Board br;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        /*Image img = new Image(this.getClass().getResourceAsStream("/sprites/spr_1.png"));
        gc.drawImage(img, 0, 0);*/
        br = new Board();
        drawBoard();
    }    
    
    
    @FXML
    public void canvasOnMouseClicked(MouseEvent m) {
    	if (!picked) {
        	int x, y;
        	sY = x = Math.floorDiv((int) m.getX(), 46);
        	sX = y = Math.floorDiv((int) m.getY(), 46);
			MainApp.logger.warn(sX + " " +sY +"\n" + tX +" " + tY);
            Image img = new Image(this.getClass().getResourceAsStream("/sprites/spr_edge.png"));
            gc.drawImage(img, x*46, y*46);
            //br.getElement(sX, sY).showValues();
        	picked = true; 
        	return;
    	}
    	if (picked) {
        	tY = Math.floorDiv((int) m.getX(), 46);
        	tX = Math.floorDiv((int) m.getY(), 46); 
          //  br.getElement(sX, sY).showValues();
          //  br.getElement(tX, tY).showValues();
        	br.switchPositions(sX,sY,tX,tY);

        	if (br.checkRecursiveHorStart(tX, tY, true) || br.checkRecursiveVerStart(tX, tY, true))
        		br.fallBoard();
        	else
        		br.switchPositions(sX,sY,tX,tY);
    		drawBoard();
    		picked = false;
    	}

    }
    @FXML
    public void canvasOnDragDetected(MouseEvent m) {
    	int x, y;
    	x = (int) (m.getSceneX() / 46);
    	y = (int) (m.getSceneY() / 46);


    	MainApp.logger.warn(x + " " + y);
    }
    @FXML   
    public void canvasOnDragOver(DragEvent m) {   
    	Image img = new Image(this.getClass().getResourceAsStream("/sprites/spr_1.png"));
    	gc.drawImage(img, m.getSceneX()-24, m.getSceneY()-24);
    	MainApp.logger.warn(m.getSceneX() + " " + m.getSceneY());
    }
    
    public void initData(String nev) {
        playerName.setText(nev);        
    }
    
    private void drawBoard() {
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    	for (int i=0; i<8; i++) {
    		for (int j=0; j<8; j++) {
    			int tmp = br.getElement(j, i);
    			if (tmp == -1)
    				continue;
    			String tmpString = "/sprites/spr_" + Integer.toString(tmp+1)+".png";
    	        Image img = new Image(this.getClass()
    	        		.getResourceAsStream(tmpString));
    	        gc.drawImage(img, i * 46, j * 46);
    		}
    	}
    }
}
