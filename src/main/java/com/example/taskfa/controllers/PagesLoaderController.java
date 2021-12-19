package com.example.taskfa.controllers;

import com.example.taskfa.model.ScreenLoader;
import com.example.taskfa.model.User;
import com.example.taskfa.utils.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PagesLoaderController implements Initializable {
    @FXML
    private BorderPane mainPane;
    @FXML
    private Label projectIdpassed;
    private int projectId;
    User user = UserSession.getCurrentUser();

    public void goToVcs() {
        ScreenLoader screen = new ScreenLoader();
        Pane view;
        if (user.isAdmin()) {
            view = screen.getPage("vcsViewAdmin");
        } else {
            view = screen.getPage("vcsView");
        }

        mainPane.setCenter(view);
    }

    public void goToChat() {
        ScreenLoader screen = new ScreenLoader();
        Pane view = screen.getPage("chatView");
        mainPane.setCenter(view);
    }

    public void goToTasks() {
        ScreenLoader screen = new ScreenLoader();
        Pane view = screen.getPage("TASK");
        mainPane.setCenter(view);
    }

    public void goToHome() throws IOException {
        GridPane homeSideBar;
        FXMLLoader fxmlLoader = new FXMLLoader();
        ScreenLoader screen = new ScreenLoader();
        Pane view = screen.getPage("projectView");
        mainPane.setCenter(view);
    }

    public void gotoOverview() {
        ScreenLoader screen = new ScreenLoader();
        Pane view = screen.getPage("OverView");
        mainPane.setCenter(view);
    }

    public void goToResources() {
        ScreenLoader screen = new ScreenLoader();
        Pane view = screen.getPage("Ressource");
        mainPane.setCenter(view);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = UserSession.getCurrentUser();
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader();
            FXMLLoader fxmlLoader2 = new FXMLLoader();
            fxmlLoader1.setLocation(getClass().getResource(user.getMenu().showSideBar()));
            GridPane sideBar = fxmlLoader1.load();
            fxmlLoader2.setLocation(getClass().getResource(user.getMenu().showOverView()));
            GridPane center = fxmlLoader2.load();
            mainPane.setLeft(sideBar);
            BorderPane.setMargin(mainPane.getLeft(), new Insets(-50,0,0,0));
            mainPane.setCenter(center);
            projectIdpassed.setText(String.valueOf(projectId));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
