package com.example.taskfa.controllers;

import com.example.taskfa.model.Message;
import com.example.taskfa.model.User;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ChatItemController {

    @FXML
    private ImageView memberImage;

    @FXML
    private Text memberName;

    @FXML
    private Text messageContent;

    private Message message;

    public void setData(Message message){
        this.message = message;
        memberName.setText(message.getSender().getLastName());
        messageContent.setText(message.getMessage());
    }

}