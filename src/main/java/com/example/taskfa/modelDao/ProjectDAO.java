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
                    System.out.println(projectId);
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
}
