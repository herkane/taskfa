package com.example.taskfa.controllers.chat;

import com.example.taskfa.utils.IDandUsers;
import com.example.taskfa.utils.UserSession;
import com.example.taskfa.model.Message;
import com.example.taskfa.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ChatViewController implements Initializable {
    @FXML
    public TextField message;
    @FXML
    public ScrollPane scrollMessages;
    @FXML
    private GridPane grid;
    @FXML
    private GridPane gridChat;

    Thread thread;

    private final List<Message> messages = new ArrayList<>();

    private void getMessages() {
        /*
        Message message;
        Date date = Calendar.getInstance().getTime();
        HashMap<String, User> users = IDandUsers.getLoginInfo();
        DateFormat df = new SimpleDateFormat("hh:mm");

        message = new Message();
        message.setSender(users.get("Lena Prince"));
        message.setMessage("Bonjour tous le monde");
        message.setDate_sent(df.format(date));
        messages.add(message);

        message = new Message();
        message.setSender(users.get("Raphael Sanders"));
        message.setMessage("Salut");
        message.setDate_sent(df.format(date));
        messages.add(message);

        message = new Message();
        message.setSender(users.get("Mariah Walker"));
        message.setMessage("Bonjour");
        message.setDate_sent(df.format(date));
        messages.add(message);

        message = new Message();
        message.setSender(users.get("Donald Michael"));
        message.setMessage("Bonjour ");
        message.setDate_sent(df.format(date));
        messages.add(message);

        message = new Message();
        message.setSender(users.get("Mariah Walker"));
        message.setMessage("Lorem");
        message.setDate_sent(df.format(date));
        messages.add(message);

        message = new Message();
        message.setSender(users.get("Lena Prince"));
        message.setMessage("test 123");
        message.setDate_sent(df.format(date));
        messages.add(message);

        message = new Message();
        message.setSender(users.get("Raphael Sanders"));
        message.setMessage("Helloo");
        message.setDate_sent(df.format(date));
        messages.add(message);

        message = new Message();
        message.setSender(users.get("Lena Prince"));
        message.setMessage("Ahlan");
        message.setDate_sent(df.format(date));
        messages.add(message);

         */
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
            Update Grid Message List With Messages
         */
        /*
        getMessages();
        try {
            fillChat();
        } catch (IOException e) {
            e.printStackTrace();
        }
         */
    }

    public void onEnterMessage() throws IOException {
        /*
        String msg = message.getText();
        Message messageToSend = new Message();
        Date date = Calendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("hh:mm");
        messageToSend.setSender(IDandUsers.getUserObject(IDandUsers.getCurrentUser()));
        messageToSend.setMessage(msg);
        messageToSend.setDate_sent(df.format(date));
        messages.add(messageToSend);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/views/messageItem.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        ChatItemController chatItemController = fxmlLoader.getController();
        chatItemController.setData(messageToSend);
        grid.add(anchorPane, 0, messages.size());
        GridPane.setMargin(anchorPane, new Insets(10));
        GridPane.setHalignment(anchorPane, HPos.RIGHT);
        message.setText("");
        scrollMessages.needsLayoutProperty().addListener((observable, oldValue, newValue) -> {
            scrollMessages.setVvalue(scrollMessages.getVmax());
        });

         */
    }

    public void fillChat() throws IOException {
        int column = 0;
        int row = 1;
        for (Message value : messages) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/views/messageItem.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            ChatItemController chatItemController = fxmlLoader.getController();
            chatItemController.setData(value);
            if (column == 1) {
                column = 0;
                row++;
            }
            grid.add(anchorPane, column++, row);
            GridPane.setMargin(anchorPane, new Insets(10));
        }
        scrollMessages.needsLayoutProperty().addListener((observable, oldValue, newValue) -> {
            scrollMessages.setVvalue(Double.MAX_VALUE);
        });
    }



}
