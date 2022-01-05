package com.example.taskfa.controllers;

import com.example.taskfa.model.Status;
import com.example.taskfa.model.Task;
import com.example.taskfa.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class DialogController  implements Initializable {

    @FXML
    private TextArea taskDetails;

    @FXML
    private TextField taskTitle;

    @FXML
    private ChoiceBox<String> memberChoiceBox;

    private final ArrayList<User> users =  new ArrayList();
    private ObservableList<Task> observableList;

    public void setObservableList(ObservableList<Task> observableList) {
        this.observableList = observableList;
    }

    @FXML
    void submit() {
        System.out.println("submit task clicked");
        String title = taskTitle.getText().trim();
        String details = taskDetails.getText().trim();
        //int memberId = users.get(Integer.parseInt(memberChoiceBox.getId())).getIdUser();
        String member = memberChoiceBox.getSelectionModel().getSelectedItem().trim();
        observableList.add(new Task(title, details, member));
        closeStage();
    }

    private void closeStage() {
        Stage stage  = (Stage) taskTitle.getScene().getWindow();
        stage.close();
    }

    public ArrayList<User> getUsers(){
        users.add(new User("Herkane","Achraf",1 ));
        users.add(new User("Elhadeg","Fatima",2 ));
        users.add(new User("Boussoufiane","Aissam",3 ));
        users.add(new User("Laouissi","Anass",4 ));
        System.out.println("How many users in list : " + users.size());
        for (int i = 0; i< users.size();i++) {
            memberChoiceBox.getItems().add(users.get(i).getFirstName() + " " + users.get(i).getLastName());
        }
        System.out.println("How many users : " + memberChoiceBox.getItems().size());
        return users;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getUsers();
    }
}

