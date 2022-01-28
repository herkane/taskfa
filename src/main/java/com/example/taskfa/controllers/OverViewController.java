package com.example.taskfa.controllers;

import com.example.taskfa.controllers.tasks.TaskStatus;
import com.example.taskfa.controllers.tasks.user.TasksModel;
import com.example.taskfa.model.*;
import com.example.taskfa.modelDao.TaskDAO;
import com.example.taskfa.utils.UserSession;
import javafx.collections.ObservableList;
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
import java.sql.SQLException;
import java.util.*;

public class OverViewController {

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
        //setChatMessages();
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






}
