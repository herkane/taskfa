package com.example.taskfa.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInController {
    @FXML
    private Button signInButton;
    @FXML
    private Button signUpButton;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Button recoverPassword;

    public void goToSignUp() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Stage stage = (Stage) signInButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

}