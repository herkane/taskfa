package com.example.taskfa.modelDao;

import com.example.taskfa.model.Message;
import com.example.taskfa.model.Project;
import com.example.taskfa.model.User;
import com.example.taskfa.utils.DBConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ChatDAO {

    public static ObservableList<Message> searchMessages(int projectId) throws SQLException, ClassNotFoundException {
        String selectStm = "SELECT message_content,time_sent,lastName,image,iduser FROM message " +
                "INNER JOIN user ON user_iduser = iduser WHERE project_projectid = "+projectId+" " +
                "ORDER BY time_sent ASC;";
        try {
            ResultSet rsProjects = DBConfig.dbExecuteQuery(selectStm);
            ObservableList<Message> projectlist = getMessageList(rsProjects);
            return projectlist;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    public static ObservableList<Message> searchMessagesLimit(int projectId) throws SQLException, ClassNotFoundException {
        String selectStm = "SELECT message_content,time_sent,lastName,image,iduser FROM message " +
                "INNER JOIN user ON user_iduser = iduser WHERE project_projectid = "+projectId+" " +
                "ORDER BY time_sent ASC LIMIT 10;";
        try {
            ResultSet rsProjects = DBConfig.dbExecuteQuery(selectStm);
            ObservableList<Message> projectlist = getMessageList(rsProjects);
            return projectlist;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    private static ObservableList<Message> getMessageList(ResultSet rs) throws SQLException {
        ObservableList<Message> messagesList = FXCollections.observableArrayList();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
        DateFormat dfDisplay = new SimpleDateFormat("hh-mm");
        while (rs.next()) {
            Message message = new Message();
            User user = new User();
            user.setIdUser(rs.getInt("iduser"));
            user.setLastName(rs.getString("lastName"));
            Blob blob = rs.getBlob("image");
            InputStream inputStream = blob.getBinaryStream();
            Image image = new Image(inputStream);
            user.setImage(image);
            message.setSender(user);
            message.setMessage(rs.getString("message_content"));
            message.setDate_sent(dfDisplay.format(rs.getTimestamp("time_sent")));
            System.out.println(message);
            messagesList.add(message);
        }

        return messagesList;
    }

    public static void InsertMessage( String message_content, String time_sent, int user_iduser, int project_projectid) throws ClassNotFoundException {

        String insertStmtprepared = "INSERT INTO message " +
                "( message_content, time_sent, user_iduser,  project_projectid) " +
                "VALUES " +
                "(?,?,?,?);";

        try {
            DBConfig.dbConnect();
            Connection conn = DBConfig.getConn();
            PreparedStatement ps = conn.prepareStatement(insertStmtprepared);
            ps.setString(1, message_content);
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
            Timestamp t = new Timestamp(formatter.parse(time_sent).getTime());

            ps.setTimestamp(2, t);
            ps.setInt(3, user_iduser);
            ps.setInt(4,project_projectid);
            ps.executeUpdate();
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

    }
}