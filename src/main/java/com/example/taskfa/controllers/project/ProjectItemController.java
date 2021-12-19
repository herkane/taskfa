package com.example.taskfa.controllers.project;

import com.example.taskfa.controllers.PagesLoaderController;
import com.example.taskfa.controllers.chat.ChatItemController;
import com.example.taskfa.model.Project;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ProjectItemController {
    @FXML
    private Label meetingDate;

    @FXML
    private Label projectDateCreation;

    @FXML
    private Label projectNumMembers;

    @FXML
    private Label projectStatus;

    @FXML
    private Label projectTitle;
    @FXML
    private Button invisibleProjectId;

    private Project project;

    public void setData(Project project) {
        DateFormat yearFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm");
        this.project = project;
        invisibleProjectId.setText(Integer.toString(project.getProjectId()));
        projectTitle.setText(project.getTitle());
        projectDateCreation.setText(yearFormat.format(project.getCreatedDate()));
        if (project.getNextMeeting() == null){
            meetingDate.setText("no meeting");
        } else {
            meetingDate.setText(dateFormat.format(project.getNextMeeting()));
        }
        meetingDate.setFont(Font.font("System", 12));
        projectNumMembers.setText(Integer.toString(project.getMembersNum()));
        projectStatus.setText("In progress");
    }

    public void mouseClick() throws IOException {
        int projectId = Integer.parseInt(invisibleProjectId.getText());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/views/pagesLoader.fxml"));
        BorderPane border = fxmlLoader.load();
        PagesLoaderController pagesLoaderController = fxmlLoader.getController();
        pagesLoaderController.setProjectId(projectId);
        Stage window = (Stage) invisibleProjectId.getScene().getWindow();
        window.setScene(new Scene(border));
        window.setFullScreen(true);

        System.out.println(projectId);
    }
}
