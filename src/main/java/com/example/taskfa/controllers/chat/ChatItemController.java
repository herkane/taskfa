package com.example.taskfa.controllers.chat;

import com.example.taskfa.model.Message;
import com.example.taskfa.model.User;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

public class ChatItemController {

    @FXML
    private ImageView memberImage;

    @FXML
    private Text memberName;

    @FXML
    private Text messageContent;

    @FXML
    private Text timeSentMessage;

    @FXML
    private GridPane messageContainer;

    @FXML
    private Pane topBarLayout;

    private Message message;

    public void setData(Message message){
        this.message = message;
        memberName.setText(message.getSender().getLastName());
        messageContent.setText(message.getMessage());
        timeSentMessage.setText(String.valueOf(message.getDate_sent()));
        messageContainer.setMinHeight(messageContent.getLayoutBounds().getHeight());
        if (messageContainer == null) {
            System.out.println("null");
        }
    }

    public void changeColor() {
        topBarLayout.setStyle("");
    }
}