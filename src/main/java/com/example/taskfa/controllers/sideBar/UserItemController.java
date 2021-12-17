package com.example.taskfa.controllers.sideBar;


import com.example.taskfa.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class UserItemController {

    @FXML
    private Label userConnStatus;

    @FXML
    private Label userFullName;

    @FXML
    private ImageView userImage;


    private User user;

    public void setData(User user) {
        this.user = user;
        userConnStatus.setText(user.getStatus());
        userFullName.setText(user.getLastName() + " " + user.getFirstName());
        userImage.setImage(user.getImage());
    }

}