package com.example.taskfa.controllers.chat;

import com.example.taskfa.model.Message;
import com.example.taskfa.modelDao.ChatDAO;
import com.example.taskfa.utils.UserSession;
import javafx.concurrent.Task;
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
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ChatViewController{
    @FXML
    public TextField message;
    @FXML
    public ScrollPane scrollMessages;
    @FXML
    private GridPane grid;
    @FXML
    private GridPane gridChat;

    private Executor exec ;

    private List<Message> messages = new ArrayList<>();
    private int projectId;

    private void getMessages() {
        try {
            messages = ChatDAO.searchMessages(projectId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
         /*
        Task<List<Message>> chatSearchTask = new Task<List<Message>>() {
            @Override
            public List<Message> call() throws Exception {
                return ChatDAO.searchMessages(projectId);
            }
        };
        chatSearchTask.setOnFailed(e -> {
            chatSearchTask.getException().printStackTrace();
            // inform user of error...
        });

        chatSearchTask.setOnSucceeded(e ->
                // Task.getValue() gives the value returned from call()...
                messages = chatSearchTask.getValue()
        );

        exec.execute(chatSearchTask);
          */
    }


    public void onEnterMessage() throws IOException {

        String msg = message.getText();
        Message messageToSend = new Message();
        Date date = Calendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
        DateFormat dfDisplay = new SimpleDateFormat("hh-mm");
        messageToSend.setSender(UserSession.getCurrentUser());
        messageToSend.setMessage(msg);
        messageToSend.setDate_sent(dfDisplay.format(date));
        messages.add(messageToSend);
        try {
            ChatDAO.InsertMessage(msg, df.format(date), UserSession.getCurrentUser().getIdUser(), projectId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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


    public void loadFXML(int projectIdpassed) {
         /*
            Update Grid Message List With Messages
         */
        // create executor that uses daemon threads:

        projectId = projectIdpassed;
        exec = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t ;
        });
        getMessages();
        try {
            fillChat();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}



