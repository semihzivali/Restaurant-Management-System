package Controllers;

import Models.DataBaseConnection;
import Services.InsertUser;
import application.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class AddController {
	
	@FXML
    private Button add;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    
    private InsertUser insertUser = new InsertUser();
    
    @FXML
    public void GetData(ActionEvent actionEvent) {
        System.out.println(username.getText());
        System.out.println(password.getText());
        insertUser.writeToDatabase(username.getText(), password.getText(), "manager");
    }
	
	
}
