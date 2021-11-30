package com.example.taskfa.controllers;

import com.example.taskfa.model.Message;
import com.example.taskfa.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ChatViewController implements Initializable {

    @FXML
    private GridPane grid;

    private List<Message> messages = new ArrayList<>();

    private List<Message> getData() {
        List<Message> messages = new ArrayList<>();
        Message message;
        Date date = Calendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("hh:mm");
        for (int i=0; i<7 ; i++) {
            message = new Message();
            message.setSender(new User("Aissam","Boussoufiane"));
            message.setMessage("Bonjour GI");
            message.setDate_sent(df.format(date));
            messages.add(message);
        }
        return messages;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messages.addAll(getData());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < messages.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getClassLoader().getResource("message.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ChatItemController chatItemController = fxmlLoader.getController();
                chatItemController.setData(messages.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
