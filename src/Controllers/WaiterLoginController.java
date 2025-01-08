package Controllers;

import Services.ReadUser;
import Models.DataBaseConnection;
import application.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import Utils.AlertHelper;

// LoggedInUser sınıfını ekliyoruz
import Models.User;

public class WaiterLoginController {
    
    @FXML
    private Button waiter_login_button;
    @FXML
    private Button back_button;
    @FXML
    private TextField waiter_username;
    @FXML
    private TextField waiter_password;
    
    private ReadUser userService = new ReadUser();
    
    @FXML
    private void checkLogin(ActionEvent event) {
        // Kullanıcı adı ve şifreyi alıyoruz
        String username = waiter_username.getText();
        String password = waiter_password.getText();
        
        // Veritabanında kullanıcıyı kontrol ediyoruz ve ID'sini alıyoruz
        int waiterId = userService.getUserId(username, password, "waiter");
        
        if (waiterId != -1) {
            // Eğer kullanıcı doğruysa, ID'yi LoggedInUser'a kaydediyoruz
            User.setWaiterId(waiterId);
            
            System.out.println("Login successful! User ID: " + waiterId);
            AlertHelper.showAlert(AlertType.INFORMATION, "Login Successful", "Welcome, " + username + "!");
            
            // Ana sayfaya geçiş
            SceneManager.getInstance().changeScene("/Views/WaiterScreen.fxml");
        } else {
            // Kullanıcı adı veya şifre yanlışsa hata mesajı gösterme
            System.out.println("Invalid username or password.");
            AlertHelper.showAlert(AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }
    
    public void back(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/FirstScreen.fxml");
    }
}
