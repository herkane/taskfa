package com.example.taskfa.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SignUpController {
    @FXML
    private Button reduceBtn,closeBtn,uploadBtn;


    @FXML
    private ListView listview;

    public void sfc(ActionEvent event){
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
        Stage window = (Stage) reduceBtn.getScene().getWindow();
        window.setScene(new Scene(root));
        window.centerOnScreen();
        window.setFullScreen(true);
    }
}
