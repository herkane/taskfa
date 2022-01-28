package com.example.taskfa.controllers.sideBar;

import com.example.taskfa.model.Project;
import com.example.taskfa.model.User;
import com.example.taskfa.utils.DBConfig;
import com.example.taskfa.utils.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class SidebarDao {


    public static ObservableList<User> searchUser(int projectid) throws SQLException, ClassNotFoundException {
        String selectStm = "SELECT * FROM user " +
                "INNER JOIN user_has_project ON user_has_project.user_iduser =user.iduser" +
                " WHERE project_projectid = "+projectid+" ;";
        try {
            ResultSet rsUser = DBConfig.dbExecuteQuery(selectStm);
            ObservableList<User> resultSet = getUserlist(rsUser);
            return resultSet;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    private static User getUserFromResultSet(ResultSet rs) throws SQLException
    {
        User user = null;
        if (rs.next()) {
            user = new User();
            user.setIdUser(rs.getInt("iduser"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setStatus(rs.getString("status"));
            Blob blob = rs.getBlob("image");
            InputStream inputStream = blob.getBinaryStream();
            Image image = new Image(inputStream);
            user.setImage(image);
        }
        return user;
    }




    public static ObservableList<User>getUserlist(ResultSet rs) throws SQLException , ClassNotFoundException {
        ObservableList<User> Userlist = FXCollections.observableArrayList(); {
            User user = null;
            while (rs.next()) {
                user = new User();
                user.setIdUser(rs.getInt("iduser"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setStatus(rs.getString("status"));
                Blob blob = rs.getBlob("image");
                InputStream inputStream = blob.getBinaryStream();
                Image image = new Image(inputStream);
                user.setImage(image);
                if (UserSession.getCurrentUser().getIdUser() != user.getIdUser()){
                    Userlist.add(user);

                }
            }

            return Userlist;
        }
    }


}
