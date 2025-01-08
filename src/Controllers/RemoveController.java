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
        // Get username and password
        String _username = username.getText();
        String _password = password.getText();

        if (_username.isEmpty() || _password.isEmpty()) {
            AlertHelper.showAlert(AlertType.WARNING, "Warning", "Enter your username and password.");
            return;
        }

        // Deleting a user
        boolean success = userService.removeUser(_username, _password);

        if (success) {
        	AlertHelper.showAlert(AlertType.INFORMATION, "Success", "User deleted successfully.");
        } else {
        	AlertHelper.showAlert(AlertType.ERROR, "Error", "Username or password is incorrect.");
        }

        username.clear();
        password.clear();
    }
    
    public void back(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/ManagerScreen.fxml");
    }
 
}
	
