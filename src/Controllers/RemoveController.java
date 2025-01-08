package Controllers;

import Utils.AlertHelper;
import Services.UserService;
import application.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;

public class RemoveController {
	
	@FXML
    private Button remove_button;
	@FXML
    private Button back_button;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    
    private UserService userService= new UserService();
    
    @FXML
    private void deleteData() {
        // Kullanıcı adı ve şifreyi al
        String _username = username.getText();
        String _password = password.getText();

        if (_username.isEmpty() || _password.isEmpty()) {
            AlertHelper.showAlert(AlertType.WARNING, "Uyarı", "Kullanıcı adı ve şifre girin.");
            return;
        }

        // Kullanıcıyı silme işlemi
        boolean success = userService.removeUser(_username, _password);

        if (success) {
        	AlertHelper.showAlert(AlertType.INFORMATION, "Başarılı", "Kullanıcı başarıyla silindi.");
        } else {
        	AlertHelper.showAlert(AlertType.ERROR, "Hata", "Kullanıcı adı veya şifre hatalı.");
        }

        // Alanları temizle
        username.clear();
        password.clear();
    }
    
    public void back(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/ManagerScreen.fxml");
    }
 
}
	
