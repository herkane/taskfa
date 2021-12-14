package com.example.taskfa.controllers.sideBar;

import com.example.taskfa.utils.IDandUsers;
import com.example.taskfa.utils.UserSession;
import com.example.taskfa.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class SideBarController implements Initializable  {

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
    private GridPane userList;

    @FXML
    private Label userName;

    private final List<User> users = new ArrayList<>();
    private User user = null;

    private void getUserList() {
        User user;
        Date date = Calendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("hh:mm");

        user = new User();
        user.setFirstName("Aissam");
        user.setLastName("Boussoufiane");
        user.setStatus("Active");
        user.setImgSrc("/media/profile_pic_1.jfif");
        users.add(user);

        user = new User();
        user.setFirstName("Anas");
        user.setLastName("Laouissi");
        user.setStatus("Acive");
        user.setImgSrc("/media/profile_pic_6.jpg");
        users.add(user);
        user = new User();
        user.setFirstName("Achraf");
        user.setLastName("Herkane");
        user.setStatus("Last seen 8min ago");
        user.setImgSrc("/media/profile_pic_2.jfif");
        users.add(user);
        user = new User();
        user.setFirstName("Fatima");
        user.setLastName("El hadeg");
        user.setStatus("Last seen 1h ago");
        user.setImgSrc("/media/profile_pic_4.jfif");
        users.add(user);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = UserSession.getCurrentUser();
        userName.setText(user.getFirstName());
        userLastName.setText(user.getLastName());
        userId.setText(Integer.toString(user.getIdUser()));
        getUserList();
        Image img = new Image(getClass().getResourceAsStream(user.getImgSrc()));
        userImage.setImage(img);
        for (int i=0; i<users.size();i++){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/user_list_item.fxml"));
                GridPane gridPane = fxmlLoader.load();
                UserItemController userItemController = fxmlLoader.getController();
                userItemController.setData(users.get(i));
                userList.add(gridPane, 0, i);
                GridPane.setHalignment(gridPane, HPos.CENTER);
                GridPane.setMargin(gridPane, new Insets(10));
            } catch (IOException e) {
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
        window.setFullScreen(true);
    }
}
