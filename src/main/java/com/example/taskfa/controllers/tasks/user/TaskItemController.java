package com.example.taskfa.controllers.tasks.user;

import com.example.taskfa.controllers.tasks.TaskStatus;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class TaskItemController implements Initializable {
    @FXML
    private ImageView iconSelect;

    @FXML
    private Label lblTaskName;

    @FXML
    private Button btnInfo;

    private TasksModel tasksModel;

    private Consumer<TasksModel> tasksModelConsumer;

    public void setTasksModelConsumer(Consumer<TasksModel> tasksModelConsumer) {
        this.tasksModelConsumer = tasksModelConsumer;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnInfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (tasksModel.getStatus() == TaskStatus.NOTSTARTED) {
                    tasksModel.setStatus(TaskStatus.PENDING);
                } else if (tasksModel.getStatus() == TaskStatus.PENDING) {
                    tasksModel.setStatus(TaskStatus.DONE);
                } else tasksModel.setStatus(TaskStatus.NOTSTARTED);

                tasksModelConsumer.accept(tasksModel);
            }
        });
    }

    public void setTask(TasksModel model) {
        this.tasksModel = model;
        ContextMenu menu = new ContextMenu();
        System.out.println(model.toString() + " i'm heeere");
        lblTaskName.setText(model.getTitle());
        if (model.getCompleted()) {
            btnInfo.setText("Complete");
            iconSelect.setImage(new Image(getClass().getResourceAsStream("/media/icons8_checked_filled_24px_1.png")));
            menu.getItems().add(new MenuItem("Set Task InComplete"));
        } else {
            btnInfo.setText("InComplete");
            iconSelect.setImage(new Image(getClass().getResourceAsStream("/media/icons8_checked_filled_24px.png")));
            menu.getItems().add(new MenuItem("Set Task Complete"));
        }
        lblTaskName.setContextMenu(menu);
    }
}
