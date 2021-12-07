package com.example.taskfa.controllers.project;

import com.example.taskfa.model.Project;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProjectViewController implements Initializable {
    @FXML
    private TextField createProjectTitle;

    @FXML
    private TextField joinProjectCode;

    @FXML
    private Button signOutBtn;

    @FXML
    private Label userId;

    @FXML
    private ImageView userImage;

    @FXML
    private Label userLastName;

    @FXML
    private Label userName;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;



    private List<Project> projects = new ArrayList<>();

    private List<Project> getData() {
        List<Project> projects = new ArrayList<>();
        Project project;
        Date date = Calendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        for (int i=0; i<5;i++) {
            project = new Project();
            project.setTitle("My project");
            project.setMembersNum(10);
            project.setCreatedDate(df.format(date));
            project.setNextMeeting("21/11");
            projects.add(project);
        }
        return projects;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    projects.addAll(getData());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < projects.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/projectItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ProjectItemController projectItemController = fxmlLoader.getController();
                projectItemController.setData(projects.get(i));

                if (column == 2) {
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

    public void goToVcsView() throws IOException {
        Parent root  = FXMLLoader.load(getClass().getResource("/views/vcsView.fxml"));
        Stage window = (Stage) grid.getScene().getWindow();
        window.setScene(new Scene(root));
        window.centerOnScreen();
        window.setFullScreen(true);
    }


}
