package com.example.taskfa.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class RessourceController {

    @FXML
    private Button pdfbtn,otherbtn;

    @FXML
    private ListView listViewpdf,listViewother;

    public void pdf(ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("pdf","*.pdf")
        );

        List<File> selectedFiles = fc.showOpenMultipleDialog(null);
        if (selectedFiles!=null){
            for (int i=0; i<selectedFiles.size()  ; i++) {
                listViewpdf.getItems().add(selectedFiles.get(i).getName());
            }
        }else{
            System.out.println("not valid");
        }
    }





    public void other(ActionEvent event){
        FileChooser ot = new FileChooser();


        List<File> selectedFiles = ot.showOpenMultipleDialog(null);
        if (selectedFiles!=null){
            for (int i=0; i<selectedFiles.size()  ; i++) {
                listViewother.getItems().add(selectedFiles.get(i).getName());
            }
        }else{
            System.out.println("not valid");
        }
    }






}
