package com.example.taskfa.controllers.vcs;

import com.example.taskfa.model.File;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class VcsItemController {

    @FXML
    private Label fileTitle;

    private File file;

    public void setData(File file) {
        this.file = file;
        fileTitle.setText(file.getFileName());
    }
}

