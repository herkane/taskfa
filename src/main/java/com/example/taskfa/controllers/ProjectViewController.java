package com.example.taskfa.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ProjectViewController {
    @FXML
    private TextField createProjectTitle;

    @FXML
    private TextField joinProjectCode;

    @FXML
    private Button signOutBtn;

    @FXML
    private Label userId;

    @FXML
    private ImageView userImage;

    @FXML
    private Label userLastName;

    @FXML
    private Label userName;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;
}
