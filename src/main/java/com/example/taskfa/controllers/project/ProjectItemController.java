package com.example.taskfa.controllers.project;

import com.example.taskfa.model.Project;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

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

    private Project project;

    public void setData(Project project) {
        this.project = project;
        projectTitle.setText(project.getTitle());
        projectDateCreation.setText(project.getCreatedDate());
        meetingDate.setText(project.getNextMeeting());
        meetingDate.setFont(Font.font("System", 12));
        projectNumMembers.setText(Integer.toString(project.getMembersNum()));
        projectStatus.setText("In progress");
    }
}
