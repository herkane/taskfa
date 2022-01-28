package com.example.taskfa.modelDao;

import com.example.taskfa.utils.DBConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VersionDAO {
    public static void addVersion(File file, String descrption, int uId, int pId){
        String stmt = "insert into version (file_title,status,description,version_project_id,version_user_id,file) values (?,?,?,?,?,?)";
        FileInputStream fileInputStream = null;
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            DBConfig.dbConnect();
            Connection conn = DBConfig.getConn();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(stmt);
            ps.setString(1,file.getName());
            ps.setString(2,"IN_REVIEW");
            ps.setString(3, descrption);
            ps.setInt(4, pId);
            ps.setInt(5,uId);
            ps.setBinaryStream(6,fileInputStream, (int) file.length());
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
