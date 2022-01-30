package com.example.taskfa.controllers.sideBar;

import com.example.taskfa.model.User;
import com.example.taskfa.modelDao.SidebarDao;
import com.example.taskfa.modelDao.TaskDAO;
import com.example.taskfa.utils.UserSession;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;

public class ParametreController {
    @FXML
    private Button submitBtn1;

    @FXML
    private Button submitBtn11;

    @FXML
    private ChoiceBox<String> userChoiceBtn;

    private int projectId;
    private String person;
    private ObservableList<User> users = null;
    private  SideBarController sideBarController;
    @FXML
    void kickUser(ActionEvent event) {
        String user = userChoiceBtn.getSelectionModel().getSelectedItem();
        if (user != null && !user.equals("")) {
            int userId = getUserId(user);
            try {
                SidebarDao.kickUser(projectId,userId);
                sideBarController.loadFXML(projectId);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            }
        }

    @FXML
    void deleteProject(ActionEvent event) {
        try {
            SidebarDao.deleteProject(projectId);
            ((Stage)userChoiceBtn.getScene().getWindow()).close();
            sideBarController.goProjectView();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void loadFXML(int projectIdpassed, SideBarController sideBarController) {
        projectId = projectIdpassed;
        this.sideBarController = sideBarController;
        users = getUserLists();
        setMenuItems();
        userChoiceBtn.setOnAction(actionEvent -> {
            String selectedItem = userChoiceBtn.getSelectionModel().getSelectedItem();
            person = selectedItem;
        });
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
