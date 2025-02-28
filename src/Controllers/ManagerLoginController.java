package Controllers;

import Services.Abstract.IUserService;
import Utils.AlertHelper;
import Utils.SceneManager;
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
    
    private IUserService userService;
    public ManagerLoginController(IUserService _userService) {
    	this.userService = _userService;
    }
    
    public void ManagerSignupController(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/ManagerSignup.fxml");
    }
    
    @FXML
    private void checkLogin(ActionEvent event) {
        String username = manager_username.getText();
        String password = manager_password.getText();
        
    if (userService.getUserId(username, password, "manager") != -1) {      
            System.out.println("Login successful!");
            SceneManager.getInstance().changeScene("/Views/ManagerScreen.fxml");
        } else {
            System.out.println("Invalid username or password.");
            AlertHelper.showAlert(AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }
    
    public void back(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/FirstScreen.fxml");
    }

}
