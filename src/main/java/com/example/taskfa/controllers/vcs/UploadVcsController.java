package com.example.taskfa.controllers.vcs;


import com.example.taskfa.model.Status;
import com.example.taskfa.model.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UploadVcsController implements Initializable {

    @FXML
    private TextArea changesDescriptionLabel;

    @FXML
    private Label fileNameLabel;

    @FXML
    private Button submitBtn;

    @FXML
    private Button uploadFile;

    @FXML
    private ChoiceBox<String> taskChoiceBtn;

    String fileName, task;

    private final ArrayList<Task> tasks = new ArrayList();

    public void uploadFile(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("zip","*.zip")
        );
        List<File> selectedFiles = fc.showOpenMultipleDialog(submitBtn.getScene().getWindow());
        if (selectedFiles!=null){
            fileNameLabel.setText(selectedFiles.get(0).getName());
            fileName = selectedFiles.get(0).getName();
        }else{
            System.out.println("not valid");
        }
    }

    public void submit() {
        System.out.println("File  : " + fileNameLabel.getText());
        System.out.println("Task " + task);
        System.out.println("Changes : "+ changesDescriptionLabel.getText());
        submitBtn.getScene().getWindow().hide();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tasks.addAll(getDataTask());
        setMenuItems(100300);

        taskChoiceBtn.setOnAction((event) -> {
            String selectedItem = taskChoiceBtn.getSelectionModel().getSelectedItem();
            task = selectedItem;
        });
    }
    public void setMenuItems(int UserId) {
       for (Task task : tasks) {
           if (task.getUserId() == UserId) {
               taskChoiceBtn.getItems().add("#"+ task.getTaskId() +" : "+task.getTitle());
           }
       }
    }

    public List<Task> getDataTask() {
        ArrayList<Task> tasks = new ArrayList();
        Task task;
        task = new Task(1, "Create UI","You need to create this task", Status.IN_PROGRESS, 100300);
        tasks.add(task);
        task = new Task(2, "Create UI","You need to create this task", Status.IN_PROGRESS, 100);
        tasks.add(task);
        task = new Task(3, "Fix Bug","You need to create this task", Status.IN_PROGRESS, 100300);
        tasks.add(task);
        task = new Task(4, "Create UI","You need to create this task", Status.IN_PROGRESS, 100);
        tasks.add(task);
        task = new Task(5, "Update That","You need to create this task", Status.IN_PROGRESS, 100300);
        tasks.add(task);
        task = new Task(6, "Create UI","You need to create this task", Status.IN_PROGRESS, 100);
        tasks.add(task);
        task = new Task(7, "Create UI","You need to create this task", Status.IN_PROGRESS, 100300);
        tasks.add(task);
        return tasks;
    }
}