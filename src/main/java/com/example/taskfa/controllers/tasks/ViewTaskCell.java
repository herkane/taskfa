package com.example.taskfa.controllers.tasks;

import com.example.taskfa.model.Task;
import com.example.taskfa.model.User;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class ViewTaskCell extends ListCell<Task> {
    HBox hbox = new HBox();
    Label l_name = new Label("");
    Label title = new Label("");
    Pane pane = new Pane();
    Button button = new Button("Start");

    public ViewTaskCell() {
        super();
        hbox.getChildren().addAll(l_name, title,pane /*button*/);
        HBox.setHgrow(pane, Priority.ALWAYS);
        button.setOnAction(event -> getListView().getItems().remove(getItem()));
    }

    protected void updateItem(Task item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        setGraphic(null);
        if (item != null && !empty) {
            l_name.setText(item.getL_name() + " : ");
            title.setText(item.getTitle());
            setGraphic(hbox);
        }
    }
}