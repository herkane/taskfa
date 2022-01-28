package com.example.taskfa.controllers.sideBar;

import com.example.taskfa.utils.UserSession;
import com.example.taskfa.model.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.taskfa.modelDao.ProjectDAO.addMember;
import static com.example.taskfa.modelDao.ProjectDAO.joinProject;

public class SideBarController {

    @FXML
    private TextField addMemberEmail;

    @FXML
    private Button signOutBtn;

    @FXML
    private Label userId;

    @FXML
    private ImageView userImage;

    @FXML
    private Label userLastName;

    @FXML
    private GridPane userList;

    @FXML
    private Label userName;

    private int projectIdpassed;

    private User user = null;

    ObservableList<User> Users = null;



    public void getUserList() throws SQLException, ClassNotFoundException {
        try {
            // Get Projects List from database

            Users = SidebarDao.searchUser(projectIdpassed);


        } catch (SQLException e) {
            System.out.println("Error occurred while gettin guser information from DB.\n" + e);
            throw e;
        }

    }



    public void loadFXML(int projectId) {
        projectIdpassed = projectId;
        user = UserSession.getCurrentUser();
        userName.setText(user.getFirstName());
        userLastName.setText(user.getLastName());
        userId.setText(Integer.toString(user.getIdUser()));
        try {
            getUserList();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        userImage.setImage(user.getImage());
        for (int i=0; i<Users.size();i++){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/user_list_item.fxml"));
                GridPane gridPane = fxmlLoader.load();
                UserItemController userItemController = fxmlLoader.getController();
                userItemController.setData(Users.get(i));
                userList.add(gridPane, 0, i);
                GridPane.setHalignment(gridPane, HPos.CENTER);
                GridPane.setMargin(gridPane, new Insets(10));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
        Add Member to Project
     */
    @FXML
    void addMemberToProject(MouseEvent event) {
        String email = addMemberEmail.getText();
        if (!email.equals("")) {
            try {
                System.out.println(email + " " +projectIdpassed);
                addMember(email, projectIdpassed);
                Image img = new Image(getClass().getResourceAsStream("/media/blue_notification_success.png"));
                Notifications notificationBuilder = Notifications.create()
                        .title("Invitation")
                        .text("User Invited with Success")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(4))
                        .position(Pos.TOP_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                System.out.println("clicked on notification");
                            }
                        });
                notificationBuilder.show();
            } catch (SQLException | ClassNotFoundException e) {
                Image img = new Image(getClass().getResourceAsStream("/media/reject_notification.png"));
                Notifications notificationBuilder = Notifications.create()
                        .title("Invitation")
                        .text("No user with this email")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.TOP_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                System.out.println("clicked on notification");
                            }
                        });
                notificationBuilder.show();
                e.printStackTrace();
            }
        }
    }

    public void onSignOut() throws IOException {
        UserSession.setCurrentUser(null);
        Parent root  = FXMLLoader.load(getClass().getResource("/views/SignIn.fxml"));
        Stage window = (Stage) signOutBtn.getScene().getWindow();
        window.setScene(new Scene(root));
        window.centerOnScreen();
    }

}
