package Controllers;

import Services.Abstract.IUserService;
import Utils.AlertHelper;
import Utils.SceneManager;
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
    
    private IUserService userService;
    public ManagerSignupController(IUserService _userService) {
    	this.userService = _userService;
    }
    
    
    @FXML
    public void GetData(ActionEvent actionEvent) {
        String username = manager_username.getText();
        String password = manager_password.getText();

        if (username.isEmpty() || password.isEmpty()) {
            AlertHelper.showAlert(AlertType.WARNING, "Missing Information", "Make sure you fill in all fields.");
            return;
        }

        // Insert the database.
        boolean success = userService.addUser(username, password, "manager");

        if (success) {
            AlertHelper.showAlert(AlertType.INFORMATION, "Success", "User added successfully!");
            manager_username.clear();
            manager_password.clear();
      
        } else {
            AlertHelper.showAlert(AlertType.ERROR, "Error", "There was a problem adding the user.");
        }
    }
    
    public void back(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/ManagerLoginScreen.fxml");
    }
	
	
}
