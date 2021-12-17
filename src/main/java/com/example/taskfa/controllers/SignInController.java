package com.example.taskfa.controllers;

import com.example.taskfa.model.User;
import com.example.taskfa.utils.IDandUsers;
import com.example.taskfa.utils.UserSession;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignInController  implements Initializable {
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

    @FXML
    private GridPane anchorRoot;
    @FXML
    private StackPane parentContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void goToSignUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/SignUp.fxml"));
        Scene scene= signUpButton.getScene();
        root.translateYProperty().set(scene.getHeight());
        parentContainer.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();
    }

    /*
    REDIRECT TO PROJECT VIEW WHEN LOGIN
 */
    public void goToProjectView() throws IOException {
        String email = emailinput.getText();
        String password = passwordInput.getText();
        IDandUsers.setCurrentUser(email);
        User user = IDandUsers.getUserObject(email);
        if (user == null){
            System.out.println("Sign Up please");
        } else if (user.authenticate(email, password)) {
            Parent root  = FXMLLoader.load(getClass().getResource(user.getMenu().showProjects()));
            Stage window = (Stage) signUpButton.getScene().getWindow();
            window.setScene(new Scene(root));
            window.setFullScreen(true);
        } else {
            System.out.println("Password wrong");
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




}