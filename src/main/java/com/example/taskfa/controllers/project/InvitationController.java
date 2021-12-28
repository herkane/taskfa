package com.example.taskfa.controllers.project;

import com.example.taskfa.modelDao.UserDAO;
import com.example.taskfa.utils.UserSession;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class InvitationController  {
    @FXML
    private TableView<InvitationModelTable> table;

    @FXML
    private TableColumn<InvitationModelTable, String> projectName;

    @FXML
    private TableColumn<InvitationModelTable, String> projectOwner;

    @FXML
    private TableColumn<InvitationModelTable, String> status;

    @FXML
    private TableColumn<InvitationModelTable, Integer> usersNumber;

    @FXML
    private TableColumn<InvitationModelTable, String> accept;

    @FXML
    private TableColumn<InvitationModelTable, String> decline;

    @FXML
    private TableColumn<InvitationModelTable, Integer> projectId;

    @FXML
    private TableColumn<InvitationModelTable, Integer> userid;

    private ObservableList<InvitationModelTable> invitationsList = null;


    public void loadFXML() {
        //observableList = UserDAO.getInvitations()
        try {
            invitationsList = UserDAO.getInvitations(UserSession.getCurrentUser().getIdUser());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        userid.setCellValueFactory(new PropertyValueFactory<>("userId"));
        projectId.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        projectName.setCellValueFactory(new PropertyValueFactory<>("title"));
        projectOwner.setCellValueFactory(new PropertyValueFactory<>("projectOwner"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        usersNumber.setCellValueFactory(new PropertyValueFactory<>("membersNum"));
        accept.setCellFactory(new AcceptButtonCell());
        decline.setCellFactory(new DeclineButtonCell());
        table.setItems(invitationsList);
    }
}
