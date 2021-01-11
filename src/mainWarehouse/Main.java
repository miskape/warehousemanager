package mainWarehouse;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Main runnable class of the program
 * @author maailmanpaskintiimi: Jere Moilanen, Marko Korhonen, Huy Nguyen, Miska Peltoniemi
 *
 */
public class Main extends Application {

    @Override  
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        //exit
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
			public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }  
}