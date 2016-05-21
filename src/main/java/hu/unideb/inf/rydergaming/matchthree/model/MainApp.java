package hu.unideb.inf.rydergaming.matchthree.model;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entry class of the program.
 * @author Ryder
 *
 */
public class MainApp extends Application {
	/**
	 * slf4j Logger variable.
	 */
	final static Logger logger = LoggerFactory.getLogger(MainApp.class);
	/**
	 * Sets up and starts the first scene.
	 */
    @Override
    public void start(Stage stage) throws Exception {
    	logger.info("Starting main stage.");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/startScene.fxml"));
    
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Match Three");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //File file = new File("D:/fss/MatchThree/src/main/resources/score.xml");
        //XMLParser.loadXML(file);   
    	//XMLParser.saveXML(null);
    	
        launch(args);
    }

}
