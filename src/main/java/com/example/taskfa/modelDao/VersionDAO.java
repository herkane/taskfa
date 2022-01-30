package com.example.taskfa.modelDao;

import com.example.taskfa.controllers.tasks.TaskStatus;
import com.example.taskfa.controllers.tasks.user.TasksModel;
import com.example.taskfa.model.Status;
import com.example.taskfa.model.Task;
import com.example.taskfa.model.User;
import com.example.taskfa.model.VersionFile;
import com.example.taskfa.utils.DBConfig;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VersionDAO {

    public static void addVersion(File file, String descrption, int uId, int pId){
        String stmt = "insert into version (file_title,status,description,project_id,user_id,file) values (?,?,?,?,?,?)";
        FileInputStream fileInputStream = null;
        System.out.println("P : " + pId + " U : " + uId);
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

    public static List<VersionFile> getVersions(int pId){
        String stmt = "select * from version where project_id = " + pId;
        List<VersionFile> versions = new ArrayList<>();
        try {
            ResultSet rs = DBConfig.dbExecuteQuery(stmt);
            while (rs.next()) {
                VersionFile file = new VersionFile();
                file.setName(rs.getString("file_title"));
                file.setDescription(rs.getString("description"));
                versions.add(file);
            }
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Error in DAO");
            e.printStackTrace();
        }
        return versions;
    }

    public static VersionFile getLatestVersion(int pId) throws SQLException, ClassNotFoundException {
        String stmt = "SELECT * FROM version where project_id = " + pId + " ORDER BY project_id DESC LIMIT 1";
        VersionFile file = new VersionFile();
        try {
            ResultSet rs = DBConfig.dbExecuteQuery(stmt);
            while (rs.next()) {
                file.setName(rs.getString("file_title"));
                file.setDescription(rs.getString("description"));
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }

}
