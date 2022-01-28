package com.example.taskfa.modelDao;

import com.example.taskfa.model.Message;
import com.example.taskfa.model.Project;
import com.example.taskfa.model.User;
import com.example.taskfa.utils.DBConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ChatDAO {

    public static ObservableList<Message> searchMessages(int projectId) throws SQLException, ClassNotFoundException {
        String selectStm = "SELECT message_content,time_sent,lastName,image,iduser FROM message " +
                "INNER JOIN user ON user_iduser = iduser WHERE project_projectid = "+projectId+" " +
                "ORDER BY time_sent DESC;";
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
                "ORDER BY time_sent DESC LIMIT 10;";
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
        DateFormat df = new SimpleDateFormat("hh:mm");
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
            message.setDate_sent(df.format(rs.getTimestamp("time_sent")));
            System.out.println(message);
            messagesList.add(message);
        }

        return messagesList;
    }
}


