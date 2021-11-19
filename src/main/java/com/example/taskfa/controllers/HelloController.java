package com.example.taskfa.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloController {
    @FXML
    private Button signInButton;
    @FXML
    private Button signUpButton;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Button recoverPassword;


    public void signUp(ActionEvent event) throws IOException {
         Stage stage;
         Parent root;
        stage = (Stage)signUpButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/taskfa/sign_up.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.show();
    }

    public void signIn(ActionEvent event) throws IOException {

    }
}