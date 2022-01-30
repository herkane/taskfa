package com.example.taskfa.authController;

import com.example.taskfa.modelDao.ChatDAO;
import com.example.taskfa.modelDao.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConfirPasswordController {
    @FXML
    private TextField confirmpassword;

    @FXML
    private TextField password;
    private String email;
    public void loadFXML(String email) {
        this.email = email;
    }

    public void verify(ActionEvent event) {
        if (confirmpassword.getText().equals(password.getText())) {
            UserDAO.resetPassword(email, password.getText());
            ((Stage)confirmpassword.getScene().getWindow()).close();
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Password Update");
            a.setContentText("Password Updated Succesfly");
            a.showAndWait();
        }
     }
}
