package Controllers;

import Services.ReadUser;
import Utils.AlertHelper;
import Models.DataBaseConnection;
import application.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class ManagerLoginController {
	
	@FXML
    private Button manager_signup_button;
	@FXML
    private Button manager_login_button;
	@FXML
    private Button back_button;
    @FXML
    private TextField manager_username;
    @FXML
    private TextField manager_password;
    
    private ReadUser userService = new ReadUser();
    
    public void ManagerSignupController(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/ManagerSignup.fxml");
    }
    
    @FXML
    private void checkLogin(ActionEvent event) {
        // Kullanıcı adı ve şifreyi alıyoruz
        String username = manager_username.getText();
        String password = manager_password.getText();
        
        // Veritabanında kullanıcıyı kontrol ediyoruz
    if (userService.getUserId(username, password, "manager") != -1) {
            // Eğer kullanıcı doğruysa, ana sayfaya geçiş yapılabilir.
            System.out.println("Login successful!");
            SceneManager.getInstance().changeScene("/Views/ManagerScreen.fxml");
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
