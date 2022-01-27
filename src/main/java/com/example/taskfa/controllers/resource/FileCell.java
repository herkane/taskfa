package com.example.taskfa.controllers.resource;

import com.example.taskfa.controllers.RessourceController;
import com.example.taskfa.model.File;
import com.example.taskfa.modelDao.ResourcesDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class FileCell extends ListCell<String> {
    HBox hbox = new HBox();
    Label label = new Label("");
    Pane pane = new Pane();
    Button button = new Button("Del");
    Button copy = new Button("Copy");
    private int projectId;
    String cell;

    public FileCell(int projectId, String cell) {
        super();
        this.projectId = projectId;
        this.cell = cell;
        if (cell.contains("links")){
            hbox.getChildren().addAll(label, pane, button, copy);

        } else  hbox.getChildren().addAll(label, pane, button);

        HBox.setHgrow(pane, Priority.ALWAYS);
        button.setOnAction(event -> {
            System.out.println("FileCell : " + FileCell.this.getListView().getItems().get(FileCell.this.getIndex()));
            System.out.println("FileCell : " + projectId);
            ResourcesDAO.deletePdf(FileCell.this.getItem(), projectId);
            ResourcesDAO.deleteLink(FileCell.this.getItem(),projectId);
            ResourcesDAO.deleteOther(FileCell.this.getItem(), projectId);
            FileCell.this.getListView().getItems().remove(FileCell.this.getItem());
        });

        if (cell.contains("links")) {
            copy.setOnAction(event -> {
                final Clipboard clipboard = Clipboard.getSystemClipboard();
                final ClipboardContent content = new ClipboardContent();
                content.putString(FileCell.this.getListView().getItems().get(FileCell.this.getIndex()));
                clipboard.setContent(content);
            });
        }

    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        setGraphic(null);
        if (item != null && !empty) {
            label.setText(item.split(" ")[0]);
            setGraphic(hbox);
        }
    }
}
