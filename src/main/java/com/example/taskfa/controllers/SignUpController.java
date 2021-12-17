package com.example.taskfa.controllers;

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
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private Button reduceBtn,closeBtn,uploadBtn;

    @FXML
    private GridPane anchorRoot;
    @FXML
    private StackPane parentContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void upload(ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("image","*.png","*.jpeg","*.jpg"));

        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile!=null){
            uploadBtn.setText(selectedFile.getName());
        }else{
            System.out.println("Not a valid File");
        }
    }



    public void closeWin(){
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    public void reduceWin(){
        Stage stage = (Stage) reduceBtn.getScene().getWindow();
        stage.setIconified(true);
    }

    public void goToSignIn() throws IOException{
        Parent root  = FXMLLoader.load(getClass().getResource("/views/SignIn.fxml"));
        Scene scene= reduceBtn.getScene();
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
}
