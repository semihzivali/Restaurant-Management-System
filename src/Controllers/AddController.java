package Controllers;

import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import Utils.AlertHelper;
import application.SceneManager;

public class AddController {

    @FXML
    private Button create_button;
    @FXML
    private Button back_button;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private ComboBox<String> select_user_type;

    private UserService userService= new UserService();

    @FXML
    public void initialize() {
        // Add options to ComboBox
        select_user_type.getItems().addAll("waiter", "kitchen");
    }

    @FXML
    public void GetData(ActionEvent actionEvent) {
        String user = username.getText();
        String pass = password.getText();
        String userType = select_user_type.getValue();

        if (user.isEmpty() || pass.isEmpty() || userType == null) {
            AlertHelper.showAlert(AlertType.WARNING, "Missing Information", "Make sure you fill in all fields.");
            return;
        }

        // Insert the database.
        boolean success = userService.writeToDatabase(user, pass, userType);

        if (success) {
            AlertHelper.showAlert(AlertType.INFORMATION, "Success", "User added successfully!");
            // Clear the textfields.
            username.clear();
            password.clear();
            select_user_type.getSelectionModel().clearSelection();
        } else {
            AlertHelper.showAlert(AlertType.ERROR, "Error", "There was a problem adding the user.");
        }
    }
    
    public void back(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/ManagerScreen.fxml");
    }
}