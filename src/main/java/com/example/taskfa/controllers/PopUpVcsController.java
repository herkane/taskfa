package com.example.taskfa.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PopUpVcsController {

    public Button details;
    public void toDetails(ActionEvent event)  throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/popups/VcsDetailsPopUp.fxml") );
            Parent parent = fxmlLoader.load();

        Platform.runLater(()->{
                    Stage stage1=(Stage)details.getScene().getWindow();
                    stage1.setFullScreen(false);
                }
        );
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
    }
}
