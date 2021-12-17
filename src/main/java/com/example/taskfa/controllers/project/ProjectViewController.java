package com.example.taskfa.controllers.project;

import com.example.taskfa.model.Project;
import com.example.taskfa.model.User;
import com.example.taskfa.modelDao.UserDAO;
import com.example.taskfa.utils.IDandUsers;
import com.example.taskfa.utils.UserSession;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProjectViewController implements Initializable {
    @FXML
    private TextField createProjectTitle;

    @FXML
    private TextField joinProjectCode;

    @FXML
    private Button signOutBtn;

    @FXML
    private Label userId;

    @FXML
    private ImageView userImage;

    @FXML
    private Label userLastName;

    @FXML
    private Label userName;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    User user = UserSession.getCurrentUser();


    private void getData() throws SQLException, ClassNotFoundException{
       try {
           ObservableList<Project> projectsData = UserDAO.searchProjects(user.getIdUser());
           populateProjects(projectsData);
       } catch (SQLException e){
           System.out.println("Error occurred while getting projects information from DB.\n" + e);
           throw e;
       }

    }

    private void populateProjects(ObservableList<Project> projectsData) {
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < projectsData.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/projectItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ProjectItemController projectItemController = fxmlLoader.getController();
                projectItemController.setData(projectsData.get(i));

                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUserInfo(){
        userName.setText(user.getFirstName());
        userLastName.setText(user.getLastName());
        userId.setText(Integer.toString(user.getIdUser()));
        userImage.setImage(user.getImage());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUserInfo();
        try {
            getData();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void goToVcsView() throws IOException {
        Parent root  = FXMLLoader.load(getClass().getResource("/views/vcsView.fxml"));
        Stage window = (Stage) grid.getScene().getWindow();
        window.setScene(new Scene(root));
        window.centerOnScreen();
        window.setFullScreen(true);
    }

    @FXML
    void onSignOutClick(ActionEvent event) throws IOException {
        // IDandUsers.setCurrentUser(null);
        Parent root  = FXMLLoader.load(getClass().getResource("/views/SignIn.fxml"));
        Stage window = (Stage) grid.getScene().getWindow();
        window.setScene(new Scene(root));
        window.centerOnScreen();
        window.setFullScreen(false);
    }
}
