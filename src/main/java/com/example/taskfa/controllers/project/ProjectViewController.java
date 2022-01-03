package com.example.taskfa.controllers.project;

import com.example.taskfa.model.Project;
import com.example.taskfa.model.User;
import com.example.taskfa.modelDao.ProjectDAO;
import com.example.taskfa.modelDao.UserDAO;
import com.example.taskfa.utils.UserSession;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class ProjectViewController implements Initializable {

    @FXML
    private TextField createProjectTitle;
    @FXML
    private Button closeWindow,reduceWindow;

    @FXML
    private GridPane grid;

    @FXML
    private TextField joinProjectCode;

    @FXML
    private Label userId;

    @FXML
    private ImageView userImage;

    @FXML
    private Label userLastName;

    @FXML
    private Label userName;

    @FXML
    private ImageView invitationNotifimage;

    User user = UserSession.getCurrentUser();

    ObservableList<InvitationModelTable> invitationData = null;
    ObservableList<Project> projectsData = null;

    @FXML
    void createProject(MouseEvent event) throws SQLException, ClassNotFoundException {
        String projectTitle = createProjectTitle.getText();
        if (!projectTitle.equals("")) {
            ProjectDAO.createProject(projectTitle, user.getIdUser());
            getData();
        }
        createProjectTitle.setText("");
    }

    @FXML
    void joinProject(MouseEvent event) throws SQLException, ClassNotFoundException {
        int projectId = Integer.parseInt(joinProjectCode.getText());
        System.out.println(projectId);
        ProjectDAO.joinProject(projectId, user.getIdUser());
        getData();
    }



    /*
    Function that englobes all functions
     */
    public void getData() throws SQLException, ClassNotFoundException{
       try {
           // Get Projects List from database
            projectsData = ProjectDAO.searchProjects(user.getIdUser());
           // Get User's Project Invitations
            invitationData = UserDAO.getInvitations(user.getIdUser());
            // GET INIVATIONS COUNT
           // Display them
           populateProjects(projectsData);
           populateInvitation(invitationData);
       } catch (SQLException e){
           System.out.println("Error occurred while getting projects information from DB.\n" + e);
           throw e;
       }

    }

    /*
    Display List of Projects passeed In parametrs in GridCells
     */
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
    /*
        If User has invitation show invitations Icon on Top
     */
    private void populateInvitation(ObservableList<InvitationModelTable> invitationsData) {
        if (invitationsData.isEmpty()) {
            invitationNotifimage.setVisible(false);
        } else {
            invitationNotifimage.setVisible(true);
        }
    }
    /*
        This functions set Users info into sideBar
     */
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
        UserSession.setCurrentUser(null);
        Parent root  = FXMLLoader.load(getClass().getResource("/views/SignIn.fxml"));
        Stage window = (Stage) grid.getScene().getWindow();
        window.setScene(new Scene(root));
        window.centerOnScreen();
        window.setFullScreen(false);
    }

    @FXML
    void showInvitations(MouseEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/popups/popup_invitation.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load());
        } catch (IOException ex) {
            System.out.println("Error loading Pop up invitation");
        }
        InvitationController invitationController = fxmlLoader.getController();
        invitationController.loadFXML(this);
        Stage inputStage = new Stage();
        inputStage.initOwner(invitationNotifimage.getScene().getWindow());
        inputStage.setScene(newScene);
        inputStage.showAndWait();
    }

    public void closeWindow() {
        // get a handle to the stage
        Stage stage = (Stage) closeWindow.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void reduceWindow() {
        Stage stage = (Stage) reduceWindow.getScene().getWindow();
        stage.setIconified(true);
    }
}
