package com.example.taskfa.controllers.vcs;


import com.example.taskfa.model.User;
import com.example.taskfa.modelDao.VersionDAO;
import com.example.taskfa.utils.UserSession;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.WindowEvent;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class UploadVcsController implements Initializable{

    @FXML
    private TextArea changesDescriptionLabel;

    @FXML
    private Label fileNameLabel;

    @FXML
    private Button submitBtn;

    File file;
    FileChooser fc;
    private User user;
    private int projectId;



    public void uploadFile(ActionEvent event) {
        fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Compressed file","*.zip")
        );
        File selectedFile = fc.showOpenDialog(submitBtn.getScene().getWindow());
        if (selectedFile != null){
            file = selectedFile;
            fileNameLabel.setText(selectedFile.getName());
        }else{
            System.out.println("not valid");
        }
    }

    public void submit() {
        System.out.println("File  : " + fileNameLabel.getText());
        System.out.println("Changes : "+ changesDescriptionLabel.getText());
        System.out.println("Controller : " + projectId + " " + user.getIdUser());
        VersionDAO.addVersion(file,changesDescriptionLabel.getText(),user.getIdUser(),projectId);
        submitBtn.getScene().getWindow().hide();

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loadFXML(int projectIdPassed) {
        projectId = projectIdPassed;
        user = UserSession.getCurrentUser();
        System.out.println(user.getIdUser());
    }
}