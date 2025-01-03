package Controllers;

import Models.DataBaseConnection;
import application.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class ManagerLoginController {
	
	@FXML
    private Button manager_signup_button;
	@FXML
    private Button manager_login_button;
    @FXML
    private TextField manager_username;
    @FXML
    private TextField manager_password;
    
    public void ManagerSignupController(ActionEvent event) {
    	System.out.println("fdgdf");
        SceneManager.getInstance().changeScene("/Views/ManagerSignup.fxml");
    }

    public void getData(ActionEvent actionEvent) {
        System.out.println(manager_username.getText());
        System.out.println(manager_password.getText());
        DataBaseConnection.writeToDatabase(manager_username.getText(), manager_password.getText());
    }
	
	
}
