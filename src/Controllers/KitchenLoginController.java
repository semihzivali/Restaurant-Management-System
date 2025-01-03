package Controllers;

import Services.ReadUser;
import Models.DataBaseConnection;
import application.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class KitchenLoginController {
	
	@FXML
    private Button kitchen_login_button;
    @FXML
    private TextField kitchen_username;
    @FXML
    private TextField kitchen_password;
    
    private ReadUser userService = new ReadUser();
    
    public void ManagerSignupController(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/ManagerSignup.fxml");
    }
    
    @FXML
    private void checkLogin(ActionEvent event) {
        // Kullanıcı adı ve şifreyi alıyoruz
        String username = kitchen_username.getText();
        String password = kitchen_password.getText();
        
        // Veritabanında kullanıcıyı kontrol ediyoruz
        if (userService.checkUser(username, password)) {
            // Eğer kullanıcı doğruysa, ana sayfaya geçiş yapılabilir.
            System.out.println("Login successful!");
            SceneManager.getInstance().changeScene("/Views/WaiterScreen.fxml");
            // Burada sahne değiştirme veya başka bir işlem yapılabilir.
            
            // Başarı mesajı gösterme
           // showAlert(AlertType.INFORMATION, "Login Successful", "Welcome, " + username + "!");
        } else {
            // Kullanıcı adı veya şifre yanlışsa hata mesajı gösterme
            System.out.println("Invalid username or password.");
            //showAlert(AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }

}