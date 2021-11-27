package com.example.taskfa.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {
    @FXML
    private Button reduceBtn,closeBtn;

    public void closeWin(){
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    public void reduceWin(){
        Stage stage = (Stage) reduceBtn.getScene().getWindow();
        stage.setIconified(true);
    }
}
