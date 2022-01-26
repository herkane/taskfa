package com.example.taskfa.controllers.resource;

import com.example.taskfa.model.File;
import com.example.taskfa.modelDao.ResourcesDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class FileCell extends ListCell<String> {
    HBox hbox = new HBox();
    Label label = new Label("");
    Pane pane = new Pane();
    Button button = new Button("Del");

    public FileCell() {
        super();
        hbox.getChildren().addAll(label, pane, button);
        HBox.setHgrow(pane, Priority.ALWAYS);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("FileCell : " + FileCell.this.getListView().getItems().get(FileCell.this.getIndex()));
                ResourcesDAO.deletePdf(FileCell.this.getItem(),24);
                FileCell.this.getListView().getItems().remove(FileCell.this.getItem());
            }
        });

    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        setGraphic(null);
        if (item != null && !empty) {
            label.setText(item);
            setGraphic(hbox);
        }
    }
}
