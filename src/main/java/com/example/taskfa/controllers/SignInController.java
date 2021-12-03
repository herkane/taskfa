package com.example.taskfa.controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

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
    @FXML
    private TextField email, passVisible;
    @FXML
    private Button showPassword;
    @FXML
    private Button passwordShown;
    @FXML
    private ImageView showIcon;
    @FXML
    private Button closeWindow,reduceWindow;
    @FXML
    private Tooltip tooltip;

    public void goToSignUp() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/SignUp.fxml"));
        Stage window = (Stage) signUpButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    /*
    REDIRECT TO PROJECT VIEW WHEN LOGIN
 */
    public void goToProjectView() throws IOException {
        Parent root  = FXMLLoader.load(getClass().getResource("/views/pagesLoader.fxml"));
        Stage window = (Stage) signUpButton.getScene().getWindow();
        window.setScene(new Scene(root));
        window.setFullScreen(true);
    }

    public void showPassword() {
        if (!passVisible.isVisible()) {
            showIcon.setImage(new Image(getClass().getResourceAsStream("/media/hide.png")));
            passwordInput.setVisible(false);
            passVisible.setVisible(true);
            passVisible.setText(passwordInput.getText());
            tooltip.setText("Hide password");
        } else {
            showIcon.setImage(new Image(getClass().getResourceAsStream("/media/show.png")));
            passVisible.setVisible(false);
            passwordInput.setVisible(true);
            passwordInput.setText(passVisible.getText());
            tooltip.setText("Show password");
        }
        }

    public void closeWindow() {
        // get a handle to the stage
        Stage stage = (Stage) closeWindow.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void reduceWindow() {
        Stage stage = (Stage) reduceWindow.getScene().getWindow();
        stage.setIconified(true);
    }




}