package com.example.taskfa.controllers.tasks.user;

import com.example.taskfa.controllers.tasks.TaskStatus;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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

    private TaskItemController taskItemController;

    private TasksModel tasksModel;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        System.out.println("New position " + position);
        this.position = position;
    }

    // to help get position inside vbox
    private int position;

    private Consumer<TaskItemController> taskItemControllerConsumer;

    public void setTasksModelConsumer(Consumer<TaskItemController> taskItemControllerConsumer) {
        this.taskItemControllerConsumer = taskItemControllerConsumer;
    }

    public TasksModel getTasksModel() {
        return tasksModel;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        taskItemController = this;

        // TODO
        btnInfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (tasksModel.getStatus() == TaskStatus.NOTSTARTED) {
                    tasksModel.setStatus(TaskStatus.PENDING);
                    iconSelect.setImage(new Image(getClass().getResourceAsStream("/media/pending_icon_task.png")));
                    btnInfo.setText("Pending");
                    tasksModel.setCompleted(false);
                } else if (tasksModel.getStatus() == TaskStatus.PENDING) {
                    tasksModel.setStatus(TaskStatus.DONE);
                    iconSelect.setImage(new Image(getClass().getResourceAsStream("/media/icons8_checked_filled_24px_1.png")));
                    btnInfo.setText("Done");
                    tasksModel.setCompleted(true);
                } else {
                    tasksModel.setStatus(TaskStatus.NOTSTARTED);
                    iconSelect.setImage(new Image(getClass().getResourceAsStream("/media/icons8_checked_filled_24px.png")));
                    btnInfo.setText("Not Started");
                    tasksModel.setCompleted(false);
                }

                taskItemControllerConsumer.accept(taskItemController);
            }
        });
        DropShadow shadow = new DropShadow();
        btnInfo.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        lblTaskName.getScene().setCursor(Cursor.HAND);
                        if (tasksModel.getStatus() == TaskStatus.NOTSTARTED) {
                            shadow.setColor(Color.ORANGE);
                            btnInfo.setEffect(shadow);
                        } else if (tasksModel.getStatus() == TaskStatus.PENDING) {
                            shadow.setColor(Color.GREEN);
                            btnInfo.setEffect(shadow);
                        } else {
                            shadow.setColor(Color.RED);
                            btnInfo.setEffect(shadow);
                        }
                    }
                });
        btnInfo.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        lblTaskName.getScene().setCursor(Cursor.DEFAULT);
                        btnInfo.setEffect(null);
                    }
                });
    }

    public void setTask(TasksModel model, int position) {
        this.position = position;
        this.tasksModel = model;
        lblTaskName.setText(model.getTitle());
        if (model.getStatus() == TaskStatus.NOTSTARTED) {
            btnInfo.setText("Not Started");
            iconSelect.setImage(new Image(getClass().getResourceAsStream("/media/icons8_checked_filled_24px.png")));
        } else if (model.getStatus() == TaskStatus.PENDING) {
            btnInfo.setText("Pending");
            iconSelect.setImage(new Image(getClass().getResourceAsStream("/media/pending_icon_task.png")));
        } else {
            btnInfo.setText("Done");
            iconSelect.setImage(new Image(getClass().getResourceAsStream("/media/icons8_checked_filled_24px_1.png")));
        }
    }
}
