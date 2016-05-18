package hu.unideb.inf.rydergaming.matchthree;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class GameFXMLController implements Initializable {

    
    @FXML
    Label playerName;
    
    @FXML
    Canvas canvas; 
    
    @FXML
    Label points;

    Stage stage;
    Scene scene;
    
    Group root = new Group();
    
    GraphicsContext gc;
    boolean picked = false;
    boolean falling = false;
    
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
        
        //final long startNanoTime = System.nanoTime();
        
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
	            	for (int i=0; i<8;i++)
	            		for (int j=0; j<8; j++)
	            			if (br.offset[i][j]<i*46){
	            				br.offset[i][j]+=2;
	            				//System.out.println("offsetting: " + i + " " +j);
	            				//System.out.println("With value: " + br.offset[i][j]);
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
        		br.fallBoard();
        		br.setMoves(br.getMoves()-1);
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
