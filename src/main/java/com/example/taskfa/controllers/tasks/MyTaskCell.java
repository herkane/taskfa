package com.example.taskfa.controllers.tasks;

import com.example.taskfa.model.Task;
import com.example.taskfa.model.User;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class MyTaskCell extends ListCell<Task>{
    HBox hbox = new HBox();
    Label title = new Label("");
    Pane pane = new Pane();
    Button detailsBtn = new Button("Show details");
    Button startBtn = new Button("Start");

    public MyTaskCell() {
        super();
        hbox.getChildren().addAll( title,pane, detailsBtn, startBtn);
        HBox.setHgrow(pane, Priority.ALWAYS);
        startBtn.setOnAction(event -> getListView().getItems().remove(getItem()));
    }

    protected void updateItem(Task item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        setGraphic(null);
        if (item != null && !empty) {
            title.setText(item.getTitle());
            setGraphic(hbox);
        }
    }
}
