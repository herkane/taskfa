package com.example.taskfa.controllers;

import com.example.taskfa.model.File;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VcsViewController implements Initializable {

    @FXML
    private GridPane gridVersionControl;

    private List<File> files = new ArrayList<>();

    private List<File> getData() {
        List<File> files = new ArrayList<>();
        File file;
        for (int i=0; i<5; i++) {
            file = new File();
            file.setFileName("JAVATP.zip");
            files.add(file);
        }
        return files;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        files.addAll(getData());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < files.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getClassLoader().getResource("VersionControlItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                VcsItemController vcsItemController = fxmlLoader.getController();
                vcsItemController.setData(files.get(i));

                if (column == 2) {
                    column = 0;
                    row++;
                }

                gridVersionControl.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                gridVersionControl.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridVersionControl.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridVersionControl.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridVersionControl.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridVersionControl.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridVersionControl.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

