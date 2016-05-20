package hu.unideb.inf.rydergaming.matchthree.controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import hu.unideb.inf.rydergaming.matchthree.model.Board;
import hu.unideb.inf.rydergaming.matchthree.model.XMLParser;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GameFXMLController implements Initializable {

    
    @FXML
    Label playerName;
    
    @FXML
    Canvas canvas; 
    
    @FXML
    TextArea textArea;
    
    @FXML
    Label movesLeft;
    
    @FXML
    Label points;
    
    @FXML
    Button newGame;

    Stage stage;
    Scene scene;
    
   
    GraphicsContext gc;
    boolean picked = false;
    boolean falling = false;
    List<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();
    
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
        br = new Board();
        drawBoard();

        try {
			lista = XMLParser.loadXML(new File(this.getClass().getResource("/score.xml").toURI()));			
			//XMLParser.saveXML(lista, file);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        textArea.setEditable(false);
		for (List l: lista) {
			textArea.appendText(l.get(0).toString() + ":  " + l.get(1).toString());
			textArea.appendText("\n");
			
		}
		movesLeft.setText("Moves left: " + br.getMoves());
		
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
	            	for (int i=0; i<8;i++)
	            		for (int j=0; j<8; j++)
	            			if (br.offset[i][j]<i*46){
	            				br.offset[i][j]+=2;
	            			}
            	points.setText("Points:\n" + Integer.toString(br.getPoints()));
                drawBoard();
                if (br.getMoves() == 0) {
                	gc.setGlobalAlpha(0.5);
                	gc.setFill(Color.BLACK);
                	gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                	gc.setGlobalAlpha(1);
                	gc.setFill(Color.WHITE);
                	gc.setTextAlign(TextAlignment.CENTER);
                	gc.fillText("Out of moves", canvas.getWidth() / 2, canvas.getHeight() / 2);
                }
            }
        }.start();
    }    
    
    
    @FXML
    public void canvasOnMouseClicked(MouseEvent m) {
    	if (br.getMoves()==0)
    		return;
    	if (!picked) {
        	//int x, y;
        	sY = Math.floorDiv((int) m.getX(), 46);
        	sX = Math.floorDiv((int) m.getY(), 46);
			//MainApp.logger.warn(sX + " " +sY +"\n" + tX +" " + tY);

            //br.getElement(sX, sY).showValues();
        	picked = true; 
        	return;
    	}
    	if (picked) {
        	tY = Math.floorDiv((int) m.getX(), 46);
        	tX = Math.floorDiv((int) m.getY(), 46); 
          //  br.getElement(sX, sY).showValues();
          //  br.getElement(tX, tY).showValues();
        	//System.out.println(sX + " " + sY + " " + tX + " " + tY);
        	br.switchPositions(sX,sY,tX,tY);
    		//drawBoard();

        	/*if (br.checkDirections(tX, tY, true)){
        		br.fallBoard();
        	}*/
        	
        	if (br.checkRecursiveHorStart(tX, tY, true) || br.checkRecursiveVerStart(tX, tY, true)) {
        		//System.out.println("Starting showing the stuff:");
        		//br.showBoard();
        		br.fallBoard();
        		if (br.getMoves() == 0 ) {
        			try {
        				lista.add(new ArrayList<String>(Arrays.asList(playerName.getText(), Integer.toString(br.getPoints()))));
						File file = new File(this.getClass().getResource("/score.xml").toURI());
						XMLParser.saveXML(lista, file);
						file = null;
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        		boolean checkNew = true;
        		while (checkNew) {
        			checkNew = false;
        			for (int i=0;i<8;i++)
        				for (int j=0; j<8; j++) {
        		        	if (br.checkRecursiveHorStart(i, j, true) || br.checkRecursiveVerStart(i, j, true)) {
        		        		br.fallBoard();
        		        		checkNew = true;
        		        	}
        				}
        		}
        		//System.out.println("=========================");
        		//br.showBoard();
        		movesLeft.setText("Moves left: " + br.getMoves());
        	}
        		

        	else {
        		br.switchPositions(sX,sY,tX,tY);
        		System.out.println("Switched board back");
        	}
        		
    		//drawBoard();
    		picked = false;
    	}

    }
    @FXML
    public void newGameEvent(MouseEvent m) {
    	try {
            Stage stage;
            Parent root;
            //root = FXMLLoader.load(getClass().getResource("/fxml/startScene.fxml"));
            stage = (Stage) newGame.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/startScene.fxml"));
            root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(GameFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }   
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
    	        //Globe tmpOffset = br.offset[i][j];
    	        gc.drawImage(img, i*46, br.offset[j][i]);
    	        //gc.drawImage(img,i*46, j*46);
    		}
    	}
    	if (picked) {
            Image img = new Image(this.getClass().getResourceAsStream("/sprites/spr_edge.png"));
            gc.drawImage(img, sY*46, sX*46);
    	}
    }
}
