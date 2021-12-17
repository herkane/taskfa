package com.example.taskfa.controllers.vcs;

import com.example.taskfa.model.*;
import com.example.taskfa.utils.IDandUsers;
import com.example.taskfa.utils.UserSession;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;

import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VcsViewControllerAdmin implements Initializable {

    @FXML
    private GridPane gridVersionControl;

    @FXML
    private  Button version;

    @FXML
    private AnchorPane pane;
    @FXML
    private Button uploadBtn;

    @FXML
    private Label outputField;

    private List<File> files = new ArrayList<>();
    private User user = null;


    private List<File> getData() {
        List<File> files = new ArrayList<>();
        File file;
        for (int i=0; i<5; i++) {
            file = new File("JAVATP.zip", FileStatus.APPROVED);
            files.add(file);
        }
        return files;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = UserSession.getCurrentUser();
        files.addAll(getData());
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
    public void uploadProject() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/popups/upload_vcs_popUp.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load());
        } catch (IOException ex) {
            System.out.println("ERROOOOOOOR WHATCH OUT !");
        }
        Stage inputStage = new Stage();
        inputStage.initOwner(uploadBtn.getScene().getWindow());
        inputStage.setScene(newScene);
        inputStage.showAndWait();

        // String input = fxmlLoader.<UploadVcsController>getController().getValue();
        //   outputField.setText(input);
    }


    public void toPopUp(ActionEvent event) throws IOException {

       /* FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/popups/vcsPopupVersion.fxml") );
        Parent parent = fxmlLoader.load();
        Platform.runLater(()->{
                    Stage stage1=(Stage)version.getScene().getWindow();
                    stage1.setFullScreen(false);
                    stage1.setFullScreen(true);
                }
        );
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();*/


        Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/popups/vcsPopupVersion.fxml") );

        Dialog<String> dialog = new Dialog<>();
          dialog.getDialogPane().setContent(fxmlLoader.load());
         version.setOnAction(e -> {
            dialog.showAndWait();
        });
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/views/popups/vcsPopupVersion.fxml") );
        Parent parent = fxmlLoader1.load();
        Platform.runLater(()->{
                    Stage stage1=(Stage)version.getScene().getWindow();
                    stage1.setFullScreen(false);
                    stage1.setFullScreen(true);
                }
        );
        Scene scene = new Scene(parent);//Creating a scene object
        stage.setTitle("Dialog");
        stage.setAlwaysOnTop(true);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }
}



