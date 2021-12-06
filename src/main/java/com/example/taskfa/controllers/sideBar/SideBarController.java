package com.example.taskfa.controllers.sideBar;

import com.example.taskfa.controllers.UserSession;
import com.example.taskfa.model.Message;
import com.example.taskfa.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

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

    private void getUserList() {
        User user;
        Date date = Calendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("hh:mm");
        for(int i=0; i<6; i++) {
            user = new User();
            user.setFirstName("Aissam");
            user.setLastName("Boussoufiane");
            user.setStatus("Active");
            user.setImgSrc("/media/cr7.jpg");
            users.add(user);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userName.setText(UserSession.getFirstName());
        userLastName.setText(UserSession.getLastName());
        userId.setText(Integer.toString(UserSession.getIdUser()));
        getUserList();
        Image img = new Image(getClass().getResourceAsStream(users.get(0).getImgSrc()));
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
}
