package com.example.taskfa.controllers.vcs;

import com.example.taskfa.model.VersionFile;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class VcsItemController {

    @FXML
    private Label fileTitle;

    @FXML
    private Text fileChanges;

    private VersionFile file;

    public void setData(VersionFile file) {
        this.file = file;
        fileTitle.setText(file.getName());
        fileChanges.setText(file.getDescription());
    }
}

