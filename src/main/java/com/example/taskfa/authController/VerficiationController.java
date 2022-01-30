package com.example.taskfa.authController;

import com.example.taskfa.modelDao.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class VerficiationController {
    @FXML
    private TextField emailver;

    @FXML
    private TextField questionver;

    @FXML
    private Button verify;

    @FXML
    void verify(ActionEvent event) {
        // ila jab dakchi ss7i7 hna atkhdem DB
        String email = emailver.getText();
        String answer = questionver.getText();
        if (UserDAO.verifyQuestion(email,answer)){
            // tdih i dir password jdid
            ConfirPasswordController confirPasswordController;
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/views/popups/password.fxml"));
            Pane overview = null;
            try {
                overview = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(overview);
            confirPasswordController = fxmlLoader.getController();
            confirPasswordController.loadFXML(email);
            Stage inputStage = new Stage();
            inputStage.initOwner(questionver.getScene().getWindow());
            inputStage.setScene(scene);
            inputStage.showAndWait();
            ((Stage)questionver.getScene().getWindow()).close();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Verfication");
            a.setContentText("Answer Incorrect");
            a.showAndWait();
        }

    }


    public void loadFXML() {

    }
}
