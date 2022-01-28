package com.example.taskfa.modelDao;

import com.example.taskfa.controllers.tasks.TaskStatus;
import com.example.taskfa.controllers.tasks.admin.TaskItemModel;
import com.example.taskfa.controllers.tasks.user.TasksModel;
import com.example.taskfa.model.User;
import com.example.taskfa.utils.DBConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskDAO {
    public static ObservableList<User> searchUser(int projectid) throws SQLException, ClassNotFoundException {
        String selectStm = "SELECT user_iduser,firstName,lastName  FROM user " +
                "INNER JOIN user_has_project ON user_has_project.user_iduser = user.iduser" +
                " WHERE project_projectid = "+projectid+" AND role = 0";
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

    public static ObservableList<User>getUserlist(ResultSet rs) throws SQLException , ClassNotFoundException {
        ObservableList<User> Userlist = FXCollections.observableArrayList(); {
            User user = null;
            while (rs.next()) {
                user = new User();
                user.setIdUser(rs.getInt("user_iduser"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                Userlist.add(user);
            }

            return Userlist;
        }
    }

    public static ObservableList<TaskItemModel> getTasksForAdmin(int projectId) throws SQLException, ClassNotFoundException {
        String selectStm = "SELECT taskid,task_description,completed,task.status,firstName,lastName " +
                "FROM task JOIN user ON user_iduser = iduser " +
                " WHERE project_projectid = "+projectId+" ;";
        try {
            ResultSet rsTasks = DBConfig.dbExecuteQuery(selectStm);
            return getTaskForAdminList(rsTasks);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }
    private static ObservableList<TaskItemModel> getTaskForAdminList(ResultSet rs) throws SQLException {
        ObservableList<TaskItemModel> tasks = FXCollections.observableArrayList();
        while (rs.next()) {
            TaskItemModel tasksModel = new TaskItemModel();
            tasksModel.setTaskid(rs.getInt("taskid"));
            tasksModel.setTask(rs.getString("task_description"));
            tasksModel.setTaskStatus(TaskStatus.valueOf(rs.getString("status")));
            tasksModel.setFirstName(rs.getString("firstName"));
            tasksModel.setLastName(rs.getString("lastName"));
            tasks.add(tasksModel);
        }
        return tasks;
    }
    public static ObservableList<TasksModel> getTasks(int projectId, int userId) throws SQLException, ClassNotFoundException {
        String selectStm = "SELECT taskid,task_description,completed,task.status " +
                "FROM task JOIN user ON user_iduser = iduser " +
                " WHERE user_iduser = "+userId+" AND project_projectid = "+projectId+" ;";
        try {
            ResultSet rsTasks = DBConfig.dbExecuteQuery(selectStm);
            ObservableList<TasksModel> tasksList = getTaskUserList(rsTasks);
            return tasksList;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    private static ObservableList<TasksModel> getTaskUserList(ResultSet rs) throws SQLException {
        ObservableList<TasksModel> tasks = FXCollections.observableArrayList();
        while (rs.next()) {
            TasksModel tasksModel = new TasksModel();
            tasksModel.setTaskId(rs.getInt("taskid"));
            tasksModel.setTitle(rs.getString("task_description"));
            tasksModel.setCompleted(rs.getBoolean("completed"));
            tasksModel.setStatus(TaskStatus.valueOf(rs.getString("status")));
            tasks.add(tasksModel);
        }
        return tasks;
    }

    public static void updateTask(int taskId, TaskStatus taskStatus) throws SQLException, ClassNotFoundException {
        String preparedStatement = "UPDATE task" +
                " SET status = ? WHERE taskid = ?;";

        DBConfig.dbConnect();
        Connection conn = DBConfig.getConn();
        PreparedStatement ps = conn.prepareStatement(preparedStatement);
        ps.setString(1, String.valueOf(taskStatus));
        ps.setInt(2, taskId);
        ps.executeUpdate();
        System.out.println("UPDATED : " + taskStatus);
    }

    public static void assignTask(int userId, int projectId,String task) throws SQLException, ClassNotFoundException {
        String preparedStatement = "INSERT INTO task" +
                " (task_description,user_iduser,project_projectid,completed,status)" +
                " VALUES(?,?,?,0,'NOTSTARTED');";

        DBConfig.dbConnect();
        Connection conn = DBConfig.getConn();
        PreparedStatement ps = conn.prepareStatement(preparedStatement);
        ps.setString(1, task);
        ps.setInt(2, userId);
        ps.setInt(3, projectId);
        ps.executeUpdate();
    }
}
