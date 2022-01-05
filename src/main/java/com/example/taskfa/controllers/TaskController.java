package com.example.taskfa.controllers;

import com.example.taskfa.controllers.tasks.MyTaskCell;
import com.example.taskfa.controllers.tasks.ViewTaskCell;
import com.example.taskfa.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TaskController implements Initializable {

    @FXML
    private ListView taskList;
    @FXML
    private  ListView myTaskList;
    @FXML
    private StackPane taskPane;
    @FXML
    private StackPane myTaskPane;
    @FXML
    private Button add_task_btn;
    private final ObservableList<Task> taskObList = FXCollections.observableArrayList();
    private final ObservableList<Task> myTaskObList = FXCollections.observableArrayList();


    public void add_task() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/popups/add_task_dialog.fxml"));
        Parent parent = fxmlLoader.load();

        DialogController dialogController = fxmlLoader.getController();
        dialogController.setObservableList(taskObList);

        Scene scene = new Scene(parent, 600, 500);
        Stage stage = new Stage();
        stage.initOwner(add_task_btn.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Task task;
        System.out.println("Initialize triggered");

        //Users Task List
        taskList = new ListView<Task>(taskObList);
        taskList.setCellFactory(param -> new ViewTaskCell());
        taskPane.getChildren().add(taskList);

        //Initialize my observable list
        myTaskObList.add(new Task("Title","Description"));

        //My task List
        myTaskList = new ListView(myTaskObList);
        myTaskList.setCellFactory(param -> new MyTaskCell());
        myTaskPane.getChildren().add(myTaskList);
    }
}

