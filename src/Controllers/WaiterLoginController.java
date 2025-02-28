package Controllers;

import Utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import Utils.AlertHelper;
import Models.User;
import Services.Abstract.IUserService;

public class WaiterLoginController {
    
    @FXML
    private Button waiter_login_button;
    @FXML
    private Button back_button;
    @FXML
    private TextField waiter_username;
    @FXML
    private TextField waiter_password;
    
    private IUserService userService;
    public WaiterLoginController(IUserService _userService) {
    	this.userService = _userService;
    }
    
    @FXML
    private void checkLogin(ActionEvent event) {
        
        String username = waiter_username.getText();
        String password = waiter_password.getText();
        
        // We check if it matches the database using the check user method in the UserService class.
        int waiterId = userService.getUserId(username, password, "waiter");
        
        if (waiterId != -1) {
            // If the user is correct, we save the ID in User
            User.setWaiterId(waiterId);
            
            System.out.println("Login successful! User ID: " + waiterId);
        
            
            SceneManager.getInstance().changeScene("/Views/WaiterScreen.fxml");
        } else {
            // Show error message if username or password is incorrect
            System.out.println("Invalid username or password.");
            AlertHelper.showAlert(AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }
    
    public void back(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/FirstScreen.fxml");
    }
}
