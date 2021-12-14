package com.example.taskfa.modelDao;

import com.example.taskfa.model.Project;
import com.example.taskfa.model.User;
import com.example.taskfa.utils.DBConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class UserDAO {
    private static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
    public static User searchUser(String email, String password) throws NoSuchAlgorithmException {

        String selectStm = "SELECT * FROM user WHERE email = '"+email
                +"' AND password = '"+ MD5(password) +"';";

            try {
                ResultSet rsUser = DBConfig.dbExecuteQuery(selectStm);
                System.out.println("wsel");
                User user =  getUserFromResultSet(rsUser);
                return user;
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        return null;
    }
    private static User getUserFromResultSet(ResultSet rs) throws SQLException {
        User user = null;
        if (rs.next()) {
            user = new User();
            user.setIdUser(rs.getInt("iduser"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setStatus(rs.getString("status"));
        }
        return user;
    }

    public static ObservableList<Project> searchProjects(int userId) throws SQLException, ClassNotFoundException {
        String selectStm = "SELECT title,usersNumber,nextmeeting,role,project.status,projectid,created_at " +
                "FROM project " +
                "INNER JOIN user_has_project ON projectid = project_projectid " +
                "INNER JOIN user ON iduser = user_iduser " +
                "WHERE iduser = "+userId+" ;";
        try {
            ResultSet rsProjects = DBConfig.dbExecuteQuery(selectStm);
            ObservableList<Project> projectlist = getProjectsList(rsProjects);
            return projectlist;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    private static ObservableList<Project> getProjectsList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Project> projectsList = FXCollections.observableArrayList();

        while (rs.next()) {
            Project project = new Project();
            project.setProjectId(rs.getInt("projectid"));
            project.setTitle(rs.getString("title"));
            project.setCreatedDate(rs.getDate("created_at"));
            project.setMembersNum(rs.getInt("usersNumber"));
            project.setNextMeeting(rs.getDate("nextmeeting"));
            projectsList.add(project);
        }
        return projectsList;
    }
}
