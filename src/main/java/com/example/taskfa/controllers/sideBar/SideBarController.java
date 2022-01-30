package com.example.taskfa.controllers.sideBar;

import com.example.taskfa.controllers.tasks.admin.AssignTaskController;
import com.example.taskfa.modelDao.SidebarDao;
import com.example.taskfa.utils.UserSession;
import com.example.taskfa.model.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.taskfa.modelDao.ProjectDAO.addMember;

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

    @FXML
    private Button ParametreBtn;

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
        if (user.isAdmin() == false) {
            ParametreBtn.setDisable(true);
            ParametreBtn.setVisible(false);
        } else {
            ParametreBtn.setDisable(false);
            ParametreBtn.setVisible(true);
        }
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
                addMemberEmail.setText("");
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
    public void goProjectView() {
        Parent root  = null;
        try {
            root = FXMLLoader.load(getClass().getResource(user.getMenu().showProjects()));
            Stage window = (Stage) userName.getScene().getWindow();
            window.setScene(new Scene(root));
            window.setFullScreen(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void onSignOut() throws IOException {
        UserSession.setCurrentUser(null);
        Parent root  = FXMLLoader.load(getClass().getResource("/views/SignIn.fxml"));
        Stage window = (Stage) signOutBtn.getScene().getWindow();
        window.setScene(new Scene(root));
        window.centerOnScreen();
    }

    @FXML
    void onParameterClick(ActionEvent event) {
        ParametreController parametreController;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/views/popups/parametre_dialog.fxml"));
        Pane overview = null;
        try {
            overview = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(overview);
        parametreController = fxmlLoader.getController();
        parametreController.loadFXML(projectIdpassed, this);
        Stage inputStage = new Stage();
        inputStage.initOwner(userName.getScene().getWindow());
        inputStage.setScene(scene);
        inputStage.showAndWait();
    }

}
