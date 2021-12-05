package com.example.taskfa.controllers;

import com.example.taskfa.model.ScreenLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.controlsfx.control.spreadsheet.Grid;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PagesLoaderController implements Initializable {
    @FXML
    private BorderPane mainPane;

    public void goToVcs() {
        ScreenLoader screen = new ScreenLoader();
        Pane view = screen.getPage("vcsView");
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
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader();
            FXMLLoader fxmlLoader2 = new FXMLLoader();
            fxmlLoader1.setLocation(getClass().getResource("/views/SideBarView.fxml"));
            GridPane sideBar = fxmlLoader1.load();
            fxmlLoader2.setLocation(getClass().getResource("/views/OverView.fxml"));
            GridPane center = fxmlLoader2.load();
            mainPane.setLeft(sideBar);
            mainPane.setCenter(center);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
