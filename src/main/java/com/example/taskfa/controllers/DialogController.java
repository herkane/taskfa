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
    private ObservableList<Task> appMainObservableList;

    @FXML
    void submit(ActionEvent event) {
        System.out.println("submit task clicked");
        String title = taskTitle.getText();
        String details = taskDetails.getText();
        int memberId = Integer.parseInt(memberChoiceBox.getId());

        Task data = new Task(title, details, Status.NOT_STARTED, memberId);
        appMainObservableList.add(data);
        closeStage(event);
    }

    public void setAppMainObservableList(ObservableList<Task> tvObservableList) {
        this.appMainObservableList = tvObservableList;

    }

    private void closeStage(ActionEvent event) {
        Node  source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public ArrayList<User> getUsers(){
        ArrayList<User> users = new ArrayList();
        users.add(new User("Herkane","Achraf",1 ));
        users.add(new User("Herkane","Achraf",2 ));
        users.add(new User("Herkane","Achraf",3 ));
        users.add(new User("Herkane","Achraf",4 ));
        return users;
    }

    public void setUsers(){
        for (int i = 0; i< users.size();i++) {
            memberChoiceBox.getItems().add(users.get(i).getLastName() + " " + users.get(i).getFirstName());
        }
        System.out.println("How many users : " + memberChoiceBox.getItems().size());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        users.addAll(getUsers());
        setUsers();
    }
}

