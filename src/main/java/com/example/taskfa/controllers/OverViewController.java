package com.example.taskfa.controllers;

import com.example.taskfa.controllers.tasks.TaskStatus;
import com.example.taskfa.controllers.tasks.user.TasksModel;
import com.example.taskfa.model.*;
import com.example.taskfa.modelDao.ChatDAO;
import com.example.taskfa.modelDao.TaskDAO;
import com.example.taskfa.utils.UserSession;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class OverViewController {

    @FXML
    private VBox vbox_messages;

    @FXML
    private Label fileStatusVcs;

    @FXML
    private Label fileSubmitVcs;

    @FXML
    private ListView<String> inProgressListView;

    @FXML
    private ListView<String> notStartedListView;

    @FXML
    private Label officialVcsFile;

    @FXML
    private Button quitProjectButton;

    @FXML
    private ListView<String> resourceListView;

    @FXML
    private ProgressBar taskProgressBar;

    @FXML
    private Label progressPourcentage;

    private final ArrayList<Message> messages = null;
    private ArrayList<Task> tasks = null;
    private User user = null;
    private int projectIdpassed;
    /*
    public void setChatMessages() {
        messages = getChatMessages();
        Message message;
        for (int i = 0; i < messages.size(); i++) {
            message = messages.get(i);
            chatListView.getItems().add(message.getSender().getLastName()+" : "+message.getMessage());
        }
    }
     */
    public void loadFXML(int projectId) {
        // Use PROJECT ID WITH USER ID TO GET DATA
        projectIdpassed = projectId;
        user = UserSession.getCurrentUser();
        getChatMessages();
        setTaks();
        setVcsFiles();
        setResourceFiles();
    }

    public void setTaks() {
        ObservableList<TasksModel> taskInProgress = null;
        ObservableList<TasksModel> tasksNotStarted = null;
        try {
            taskInProgress = TaskDAO.getTaskDoneLimit(projectIdpassed, user.getIdUser(), TaskStatus.PENDING);
            tasksNotStarted = TaskDAO.getTaskDoneLimit(projectIdpassed, user.getIdUser(), TaskStatus.NOTSTARTED);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        int progressTerminated = 0;
        TasksModel tasksModel;
        for (int i = 0; i < taskInProgress.size() ; i++) {
                tasksModel = taskInProgress.get(i);
                inProgressListView.getItems().add("#"+tasksModel.getTaskId()+" : " + tasksModel.getTitle());
        }
        for (int i = 0; i < tasksNotStarted.size() ; i++) {
            tasksModel = tasksNotStarted.get(i);
            notStartedListView.getItems().add("#"+tasksModel.getTaskId()+" : " + tasksModel.getTitle());
        }
        /*
        float progress = (float)progressTerminated / tasks.size();
        taskProgressBar.setProgress(progress);
        progressPourcentage.setText(Math.floor(taskProgressBar.getProgress() * 100) +"%");
         */
    }
    public void setVcsFiles() {
        officialVcsFile.setText(String.valueOf(projectIdpassed));
        File file = new File("MyProjectJava.zip", FileStatus.APPROVED);
        fileSubmitVcs.setText(file.getFileName());
        fileStatusVcs.setText(file.getStatus().toString());
    }
    public void setResourceFiles() {
         resourceListView.getItems().add("PDF : JavaOriented.pdf");
         resourceListView.getItems().add("MP4 : JavaCourse.pdf");
    }

    public void quitProject() throws IOException {
        Parent root  = FXMLLoader.load(getClass().getResource("/views/projectView.fxml"));
        Stage window = (Stage) quitProjectButton.getScene().getWindow();
        window.setScene(new Scene(root));
        window.centerOnScreen();
        window.setFullScreen(true);
    }


    public void getChatMessages() {
        ObservableList<Message> messages = null;
        try {
            messages = ChatDAO.searchMessagesLimit(projectIdpassed);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Message message: messages) {
            HBox hbox = new HBox();
            Text text = new Text(message.getMessage());
            TextFlow textFlow = new TextFlow(text);
            if (message.getSender().getIdUser() == UserSession.getCurrentUser().getIdUser()){
                hbox.setAlignment(Pos.CENTER_RIGHT);
                textFlow.setStyle("-fx-color: rgb(239,242,255);" +
                        "-fx-background-color: rgb(15,125,242);" +
                        "-fx-background-radius: 20px;");
                text.setFill(Color.color(0.934, 0.945, 0.996));
                Circle circle = new Circle();
                circle.setStroke(Color.BLACK);
                circle.setFill(new ImagePattern(message.getSender().getImage()));
                hbox.getChildren().add(textFlow);
                hbox.getChildren().add(circle);
            } else {
                hbox.setAlignment(Pos.CENTER_LEFT);
                textFlow.setStyle("-fx-background-color: rgb(233,233,235);" +
                        "-fx-background-radius: 20px;");
                Circle circle = new Circle();
                circle.setStroke(Color.BLACK);
                circle.setFill(new ImagePattern(message.getSender().getImage()));
                hbox.getChildren().add(circle);
                hbox.getChildren().add(textFlow);
            }
            textFlow.setPadding(new Insets(5,5,5,10));
            vbox_messages.getChildren().add(hbox);
        }
    }







}
