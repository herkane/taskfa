package com.example.taskfa.controllers.tasks.admin;

import com.example.taskfa.model.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AssignTaskController implements Initializable {
    @FXML
    private Button submitBtn;

    @FXML
    private TextArea taskDescription;

    @FXML
    private ChoiceBox<String> userChoiceBtn;

    @FXML
    void submit(ActionEvent event) {
        System.out.println(taskDescription.getText());
        System.out.println(person);
        ((Stage)userChoiceBtn.getScene().getWindow()).close();
    }
    private String person;
    private ArrayList<String> users = null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        users = getUserLists();
        setMenuItems();
        userChoiceBtn.setOnAction(actionEvent -> {
            String selectedItem = userChoiceBtn.getSelectionModel().getSelectedItem();
            person = selectedItem;
        });
    }
    public void setMenuItems() {
        for (String user : users) {
            userChoiceBtn.getItems().add(user);
        }
    }
    public ArrayList<String> getUserLists() {
        ArrayList<String> users = new ArrayList<>();
        users.add("Aissam bsf");
        users.add("Aissam bsf");
        users.add("Aissam bsf");
        users.add("Aissam bsf");
        users.add("Aissam bsf");
        users.add("Aissam bsf");
        users.add("Aissam bsf");
        return users;
    }
}
