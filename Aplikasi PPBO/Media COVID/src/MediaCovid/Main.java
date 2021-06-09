package MediaCovid;

import java.net.Authenticator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage stage;
    public final double MIN_WINDOW_WIDTH = 390.0;
    public final double MIN_WINDOW_HEIGHT = 500.0;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("Media Covid");
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene root_scene = new Scene(root);
        stage.setScene(root_scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
