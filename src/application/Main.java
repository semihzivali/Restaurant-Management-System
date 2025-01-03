package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            SceneManager.getInstance().setPrimaryStage(primaryStage); // primaryStage atanıyor
            SceneManager.getInstance().changeScene("/Views/FirstScreen.fxml"); // İlk sahne yükleniyor
        } catch (Exception e) {
            e.printStackTrace(); // Daha iyi hata yönetimi eklenebilir
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}