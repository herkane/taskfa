package com.example.taskfa.modelDao;

import com.example.taskfa.model.Project;
import com.example.taskfa.utils.DBConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class ProjectDAO {
    /*
    SELECT USER's PROJECT LIST
     */
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

    /*
    GET PROJECTS DATA FROM RESULT SET
     */
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

    public static void createProject(String projectTitle, int userId) {
        String insertStatment = "INSERT INTO project" +
                "(title,usersNumber,nextmeeting,status,created_at)" +
                " VALUES(?,?,?,?,?);";

        String insertStatment2 = "INSERT INTO user_has_project " +
                "VALUES(?,?,?);";
        try {
            DBConfig.dbConnect();
            Connection conn = DBConfig.getConn();
            PreparedStatement ps = conn.prepareStatement(insertStatment,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, projectTitle);
            ps.setInt(2, 1);
            LocalDate date = LocalDate.now();
            ps.setDate(3, null);
            ps.setString(4, "In Progress");
            ps.setDate(5, Date.valueOf(date));
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int projectId = (int) generatedKeys.getLong(1);
                    ps = conn.prepareStatement(insertStatment2);
                    ps.setInt(1, userId);
                    ps.setInt(2, projectId);
                    ps.setInt(3, 1);
                    ps.executeUpdate();
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void joinProject(int projectId, int userId) {
        String insertStatment = "INSERT INTO user_has_project " +
                "VALUES(?,?,?);";

        try {
            DBConfig.dbConnect();
            Connection conn = DBConfig.getConn();
            PreparedStatement ps = conn.prepareStatement(insertStatment);
            ps.setInt(1, userId);
            ps.setInt(2, projectId);
            ps.setInt(3,0);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void addMember(String userEmail, int projectId) throws SQLException, ClassNotFoundException  {
        String insertStatment = "INSERT INTO user_has_invitation" +
                " VALUES(?,?,?);";
        String selectStatement = "SELECT iduser FROM user WHERE email = '"+userEmail+"';";
        try {
            ResultSet rsUser = DBConfig.dbExecuteQuery(selectStatement);
            if (rsUser.next()) {
                int userid = rsUser.getInt(1);
                System.out.println("user id : " + userid);
                Connection conn = DBConfig.getConn();
                PreparedStatement ps = conn.prepareStatement(insertStatment);
                ps.setInt(1, projectId);
                ps.setInt(2, userid);
                ps.setInt(3,0);
                ps.executeUpdate();
                System.out.println("user added successfly to invitation ");
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
        /*
        try {
            DBConfig.dbConnect();
            ResultSet rsUser = DBConfig.dbExecuteQuery(selectStatement);
            int userid = rsUser.getInt("iduser");
            System.out.println("user id : "+userid);
            Connection conn = DBConfig.getConn();
            PreparedStatement ps = conn.prepareStatement(selectStatement, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userEmail);
            ps.executeQuery();
            ps = conn.prepareStatement(insertStatment);
            ps.setInt(1, projectId);
            ps.setInt(2, userid);
            ps.setInt(3,0);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException throwables) {
            throw new SQLException("User invitation to Project failed !");
        }
         */
    }
}
