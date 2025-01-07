package Controllers;

import Services.InsertUser;
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

    private InsertUser insertUser = new InsertUser();

    @FXML
    public void initialize() {
        // ComboBox'a seçenekleri ekleyin
        select_user_type.getItems().addAll("waiter", "kitchen");
    }

    @FXML
    public void GetData(ActionEvent actionEvent) {
        String user = username.getText();
        String pass = password.getText();
        String userType = select_user_type.getValue();

        // Form kontrolü
        if (user.isEmpty() || pass.isEmpty() || userType == null) {
            AlertHelper.showAlert(AlertType.WARNING, "Eksik Bilgi", "Tüm alanları doldurduğunuzdan emin olun.");
            return;
        }

        // Veritabanına ekleme
        boolean success = insertUser.writeToDatabase(user, pass, userType);

        if (success) {
            AlertHelper.showAlert(AlertType.INFORMATION, "Başarılı", "Kullanıcı başarıyla eklendi!");
            // Alanları temizle
            username.clear();
            password.clear();
            select_user_type.getSelectionModel().clearSelection();
        } else {
            AlertHelper.showAlert(AlertType.ERROR, "Hata", "Kullanıcı eklenirken bir sorun oluştu.");
        }
    }
    
    public void back(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/ManagerScreen.fxml");
    }
}