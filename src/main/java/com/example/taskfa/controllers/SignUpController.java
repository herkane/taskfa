package com.example.taskfa.controllers;

import com.example.taskfa.model.User;
import com.example.taskfa.modelDao.UserDAO;
import com.example.taskfa.utils.DBConfig;
import com.example.taskfa.utils.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

public class SignUpController {
    @FXML
    private Button closeBtn;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private TextField email;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private PasswordField password;

    @FXML
    private Button reduceBtn;

    @FXML
    private Button signUpBtn;

    @FXML
    private Button uploadBtn;

    private File selectedFile;


    public void upload(ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("image","*.png","*.jpeg","*.jpg"));
        selectedFile = fc.showOpenDialog(null);
        if (selectedFile!=null){
            uploadBtn.setText(selectedFile.getName());
        }else{
            System.out.println("Not a valid File");
        }
    }



    public void closeWin(){
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    public void reduceWin(){
        Stage stage = (Stage) reduceBtn.getScene().getWindow();
        stage.setIconified(true);
    }

    public void goToSignIn() throws IOException{
        Parent root  = FXMLLoader.load(getClass().getResource("/views/SignIn.fxml"));
        Stage window = (Stage) reduceBtn.getScene().getWindow();
        window.setScene(new Scene(root));
        window.centerOnScreen();
        window.setFullScreen(true);
    }

    public void SignUp() throws SQLException, ClassNotFoundException {
        String firstNameData = firstName.getText();
        String lastNameData = lastName.getText();
        String emailData = email.getText();
        String passwordData = password.getText();
        String confirmPasswordData = confirmPassword.getText();
        if (checkData(firstNameData, lastNameData, emailData, passwordData, confirmPasswordData)){
            UserDAO.createUser(firstNameData, lastNameData,"active",selectedFile,emailData, passwordData);
            try {
                User user = UserDAO.searchUser(emailData, passwordData);
                UserSession.setCurrentUser(user);
                Parent root  = FXMLLoader.load(getClass().getResource(user.getMenu().showProjects()));
                Stage window = (Stage) signUpBtn.getScene().getWindow();
                window.setScene(new Scene(root));
                window.setFullScreen(true);
            } catch (NoSuchAlgorithmException | IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("SignUp Error");
            a.setContentText("Please Fill Input fields");
            a.showAndWait();
        }

    }

    private boolean checkData(String firstNameData,String lastNameData, String emailData, String passwordData, String confirmPassword) {
        if (firstNameData.equals("") || lastNameData.equals("") || emailData.equals("") || passwordData.equals("")
        || confirmPassword.equals("")) {
            return false;
        } else {
            return true;
        }
    }
}
