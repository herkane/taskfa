package com.example.taskfa.controllers;

import com.example.taskfa.model.*;
import com.example.taskfa.utils.IDandUsers;
import com.example.taskfa.utils.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class OverViewController implements Initializable {

    @FXML
    private ListView<String> chatListView;

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

    private ArrayList<Message> messages = null;
    private ArrayList<Task> tasks = null;
    private User user = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = UserSession.getCurrentUser();
        //setChatMessages();
        setTaks();
        setVcsFiles();
        setResourceFiles();
    }
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
    public void setTaks() {
        int progressTerminated = 0;
        tasks = getTasks();
        Task task;
        for (int i = 0; i < tasks.size() ; i++) {
            task = tasks.get(i);
            if (task.getSTATUS() == Status.IN_PROGRESS) {
                inProgressListView.getItems().add("#"+task.getTaskId() + ": "+task.getDescription());
            } else if (task.getSTATUS() == Status.NOT_STARTED) {
                notStartedListView.getItems().add("#"+task.getTaskId() + ": "+task.getDescription());
            } else {
                progressTerminated++;
            }
        }
        float progress = (float)progressTerminated / tasks.size();
        taskProgressBar.setProgress(progress);
        progressPourcentage.setText(Double.toString(Math.floor(taskProgressBar.getProgress()*100) ) +"%");
    }
    public void setVcsFiles() {
        officialVcsFile.setText("JavaProject.zip");
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
    /*
    public ArrayList<Message> getChatMessages() {
        ArrayList<Message> messages = new ArrayList<>();
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

        return messages;
    }
     */
    public ArrayList<Task> getTasks(){
        ArrayList<Task> tasks = new ArrayList();
        Task task;
        task = new Task(1, "Create UI","You need to create this task", Status.IN_PROGRESS, 100300);
        tasks.add(task);
        task = new Task(2, "Create UI","You need to create this task", Status.DONE, 100);
        tasks.add(task);
        task = new Task(3, "Fix Bug","You need to create this task", Status.NOT_STARTED, 100300);
        tasks.add(task);
        task = new Task(4, "Create UI","You need to create this task", Status.DONE, 100);
        tasks.add(task);
        task = new Task(5, "Update That","You need to create this task", Status.IN_PROGRESS, 100300);
        tasks.add(task);
        task = new Task(6, "Create UI","You need to create this task", Status.IN_PROGRESS, 100);
        tasks.add(task);
        task = new Task(7, "Create UI","You need to create this task", Status.NOT_STARTED, 100300);
        tasks.add(task);
        return tasks;
    }


}
