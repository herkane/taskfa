package com.example.taskfa.controllers;


import com.example.taskfa.authController.VerficiationController;
import com.example.taskfa.controllers.sideBar.ParametreController;
import com.example.taskfa.model.User;
import com.example.taskfa.modelDao.UserDAO;
import com.example.taskfa.utils.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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
    private TextField emailinput;
    @FXML
    private ImageView showIcon;
    @FXML
    private Button closeWindow,reduceWindow;
    @FXML
    private Tooltip tooltip;

    public void goToSignUp() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/SignUp.fxml"));
        Parent root1 = FXMLLoader.load(getClass().getResource("/views/SignUp.fxml"));
        Stage window = (Stage) signUpButton.getScene().getWindow();
        window.setScene(new Scene(root1));
    }

    /*
    REDIRECT TO PROJECT VIEW WHEN LOGIN
    */

    public void goToProjectView() throws IOException, NoSuchAlgorithmException {
        String email = emailinput.getText();
        String password = passwordInput.getText();
        try {
            User user = UserDAO.searchUser(email, password);
            if (user == null) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Login Error");
                a.setContentText("Login Informations error");
                a.showAndWait();
            } else {
                UserSession.setCurrentUser(user);
                Parent root  = FXMLLoader.load(getClass().getResource(user.getMenu().showProjects()));
                Stage window = (Stage) signUpButton.getScene().getWindow();
                window.setScene(new Scene(root));
                window.setFullScreen(true);
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("Error occurred while getting User information from DB.\n" + e);
            throw e;
        }
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

    @FXML
    void recoverPasswordClick(ActionEvent event) {
        VerficiationController verficiationController;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/views/popups/verification.fxml"));
        Pane overview = null;
        try {
            overview = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(overview);
        verficiationController = fxmlLoader.getController();
        verficiationController.loadFXML();
        Stage inputStage = new Stage();
        inputStage.initOwner(signUpButton.getScene().getWindow());
        inputStage.setScene(scene);
        inputStage.showAndWait();
    }

}