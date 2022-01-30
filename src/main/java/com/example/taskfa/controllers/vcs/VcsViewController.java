package com.example.taskfa.controllers.vcs;

import com.example.taskfa.model.*;

import com.example.taskfa.modelDao.VersionDAO;
import com.example.taskfa.utils.UserSession;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VcsViewController{

    @FXML
    private GridPane gridVersionControl;



    private User user = null;
    private int projectid;
    List<VersionFile> files = new ArrayList<>();

    public VcsViewController() {
    }

    public void uploadProject() {
        UploadVcsController uploadVcsController;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/views/popups/upload_vcs_popUp.fxml"));
        Pane overview = gridVersionControl;
        try {
            overview = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(overview);
        uploadVcsController = fxmlLoader.getController();
        uploadVcsController.loadFXML(projectid);
        Stage inputStage = new Stage();
        inputStage.initOwner(gridVersionControl.getScene().getWindow());
        inputStage.setScene(scene);
        inputStage.showAndWait();
    }

    public void loadFXML(int projectIdpassed) {
        projectid = projectIdpassed;
        user = UserSession.getCurrentUser();
        //List<VersionFile> files = null;
        /*files.add(new VersionFile("file1.zip", "changes1"));
        files.add(new VersionFile("file2.zip", "changes2"));
        files.add(new VersionFile("file3.zip", "changes3"));
        files.add(new VersionFile("file4.zip", "changes4"));
        files.add(new VersionFile("file5.zip", "changes5"));*/
        files.addAll(VersionDAO.getVersions(projectid));

        //files.addAll(files);
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < files.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/VersionControlItem.fxml"));

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



