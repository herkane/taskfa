package com.example.taskfa.controllers;

import com.example.taskfa.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class TaskController {

    @FXML
    private Button add_task_btn;
    private final ObservableList<Task> tvObservableList = FXCollections.observableArrayList();




    public void add_task() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/popups/add_task_dialog.fxml"));
        Parent parent = fxmlLoader.load();

        DialogController dialogController = fxmlLoader.getController();
        dialogController.setAppMainObservableList(tvObservableList);

        Scene scene = new Scene(parent, 600, 500);
        Stage stage = new Stage();
        stage.initOwner(add_task_btn.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();

    }

    public void submit() {
    }
}
