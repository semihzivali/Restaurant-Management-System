package application;

import Utils.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            SceneManager.getInstance().setPrimaryStage(primaryStage); // primaryStage atanıyor
            SceneManager.getInstance().changeScene("/Views/FirstScreen.fxml"); // First scene is loading
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
        
    }
        
}