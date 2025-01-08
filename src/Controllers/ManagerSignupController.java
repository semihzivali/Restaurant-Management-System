package Controllers;

import Services.UserService;
import Utils.AlertHelper;
import application.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class ManagerSignupController {
	
	@FXML
    private Button manager_signup_button;
	@FXML
    private Button back_button;
    @FXML
    private TextField manager_username;
    @FXML
    private TextField manager_password;
    
    private UserService userService= new UserService();
    
    
    @FXML
    public void GetData(ActionEvent actionEvent) {
        String username = manager_username.getText();
        String password = manager_password.getText();

        if (username.isEmpty() || password.isEmpty()) {
            AlertHelper.showAlert(AlertType.WARNING, "Eksik Bilgi", "Tüm alanları doldurduğunuzdan emin olun.");
            return;
        }

        // Insert the database.
        boolean success = userService.writeToDatabase(username, password, "manager");

        if (success) {
            AlertHelper.showAlert(AlertType.INFORMATION, "Başarılı", "Kullanıcı başarıyla eklendi!");
            manager_username.clear();
            manager_password.clear();
      
        } else {
            AlertHelper.showAlert(AlertType.ERROR, "Hata", "Kullanıcı eklenirken bir sorun oluştu.");
        }
    }
    
    public void back(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/ManagerLoginScreen.fxml");
    }
	
	
}
