package com.example.taskfa.controllers;


import com.example.taskfa.controllers.project.ProjectItemController;
import com.example.taskfa.controllers.resource.FileCell;
import com.example.taskfa.modelDao.ProjectDAO;
import com.example.taskfa.modelDao.ResourcesDAO;
import com.example.taskfa.utils.DBConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;


import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class RessourceController  implements Initializable{


    @FXML
    private StackPane linkPaneList;

    @FXML
    private Button otherbtn;

    @FXML
    private StackPane othersPaneList;

    @FXML
    private StackPane pdfPaneList;

    @FXML
    private Button pdfbtn;

    private int projectId;

    private File file;

    private ObservableList<String> listPdfFiles = null;
    private final ObservableList<String> listOtherFiles = FXCollections.observableArrayList(
            "File 1", "File 2", "File 3");
    private final ObservableList<String> listLinks = FXCollections.observableArrayList(
            "google.com", "facebook.com", "youtube.com");

    public void pdf(ActionEvent event) throws SQLException, ClassNotFoundException, ParseException {
        FileChooser fc = new FileChooser();
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("pdf","*.pdf")
        );
        List<File> selectedFiles = fc.showOpenMultipleDialog(pdfPaneList.getScene().getWindow());
        projectId = 24;
        if (selectedFiles!=null){
            for (int i=0; i<selectedFiles.size()  ; i++) {
                listPdfFiles.add(selectedFiles.get(i).getName() + " " + date);
                System.out.println("Controller : " + selectedFiles.get(i).getName() + " " + date);
                ResourcesDAO.addPdf(selectedFiles.get(i),projectId);
            }
        }else{
            System.out.println("PDF : not valid");
        }
    }

    public void other(ActionEvent event) throws SQLException, ClassNotFoundException, ParseException {
        FileChooser ot = new FileChooser();


        List<java.io.File> selectedFiles = ot.showOpenMultipleDialog(othersPaneList.getScene().getWindow());
        if (selectedFiles!=null){
            for (int i=0; i<selectedFiles.size()  ; i++) {
                listOtherFiles.add(selectedFiles.get(i).getName());
                ResourcesDAO.addPdf(selectedFiles.get(i),24);
            }
        }else{
            System.out.println("not valid");
        }
    }

    public void links(ActionEvent event){
        TextInputDialog inputdialog = new TextInputDialog();
        inputdialog.initOwner(pdfPaneList.getScene().getWindow());
        inputdialog.setContentText("Enter Link : ");
        inputdialog.setHeaderText("Add Link to The List");
        Optional<String> result = inputdialog.showAndWait();
        if (result.isPresent()) {
            listLinks.add(result.get());
        }
    }


    public void loadFXML(int projectIdpassed) {
        projectId = projectIdpassed;
        ListView<String> lvPdf = new ListView<>(listPdfFiles);
        ListView<String> lvOthers = new ListView<>(listOtherFiles);
        ListView<String> lvLinks = new ListView<>(listLinks);


        lvPdf.setCellFactory(param -> new FileCell());
        lvOthers.setCellFactory(param -> new FileCell());
        lvLinks.setCellFactory(param -> new FileCell());

        pdfPaneList.getChildren().add(lvPdf);
        othersPaneList.getChildren().add(lvOthers);
        linkPaneList.getChildren().add(lvLinks);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listPdfFiles = ResourcesDAO.getPdf(24);
    }
}
