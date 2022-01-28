package com.example.taskfa.controllers.tasks.admin;

import com.example.taskfa.model.User;
import com.example.taskfa.modelDao.ProjectDAO;
import com.example.taskfa.modelDao.TaskDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.sql.SQLException;

public class AssignTaskController {
    @FXML
    private Button submitBtn;

    @FXML
    private TextArea taskDescription;

    @FXML
    private ChoiceBox<String> userChoiceBtn;

    private int projectId;
    private String person;
    private ObservableList<User> users = null;
    private TaskViewController taskViewController;

    public void loadFXML(int projectIdPassed, TaskViewController taskViewController) {
        projectId = projectIdPassed;
        this.taskViewController = taskViewController;
        users = getUserLists();
        setMenuItems();
        userChoiceBtn.setOnAction(actionEvent -> {
            String selectedItem = userChoiceBtn.getSelectionModel().getSelectedItem();
            person = selectedItem;
        });
    }

    @FXML
    void submit(ActionEvent event) {
        String user = userChoiceBtn.getSelectionModel().getSelectedItem();
        String task = taskDescription.getText();
        if (user != null && !user.equals("") && !task.equals("")) {
            int userId = getUserId(user);
            try {
                TaskDAO.assignTask(userId, projectId, task);
                taskViewController.loadFXML(projectId);
                ((Stage)userChoiceBtn.getScene().getWindow()).close();
            } catch (SQLException | ClassNotFoundException e) {
                Notifications notificationBuilder = Notifications.create()
                        .title("Task")
                        .text("Task assigning "+user+" Failed.")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.CENTER);
                notificationBuilder.show();
                e.printStackTrace();
            }
        }
    }

    public void setMenuItems() {
        for (User user : users) {
            userChoiceBtn.getItems().add(user.getLastName().toUpperCase() + " " + user.getFirstName().toUpperCase());
        }
    }
    public ObservableList<User> getUserLists() {
        try {
            return TaskDAO.searchUser(projectId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getUserId(String user) {
        String[] userSplit = user.split(" ");
        for (User userC : users) {
            if (userC.getLastName().equalsIgnoreCase(userSplit[0]) && userC.getFirstName().equalsIgnoreCase(userSplit[1])) return userC.getIdUser();
        }
        return 0;
    }

}
