package com.example.taskfa.modelDao;

import com.example.taskfa.controllers.project.ProjectItemController;
import com.example.taskfa.model.Project;
import com.example.taskfa.model.User;
import com.example.taskfa.utils.DBConfig;
import com.example.taskfa.utils.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class ResourcesDAO {
    public static void addPdf(File file, int pId) throws SQLException, ClassNotFoundException, ParseException {
        String stmt = "insert into pdf (pdf_name,project_id, file) values (?,?,?)";
        System.out.println("Insert  : " + stmt);
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
            ps.setString(1,file.getName() + " " + date);
            ps.setInt(2,pId);
            ps.setBinaryStream(3,fileInputStream,(int) file.length());
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static ObservableList<String> getPdf(int id){
        String stmt = "select * from pdf where project_id = " + id;
        ObservableList<String> pdfs = FXCollections.observableArrayList();
        try {
            ResultSet rsPdf = DBConfig.dbExecuteQuery(stmt);
            while (rsPdf.next()){
                pdfs.add(rsPdf.getString("pdf_name"));
            }
            return pdfs;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Achraf");
            //e.printStackTrace();
        }
        return pdfs;
    }

    public static void deletePdf(String name, int pId){
        String stmt = "delete from pdf where pdf_name = ? and project_id = ?";
        System.out.println(stmt);

        try {
            DBConfig.dbConnect();
            Connection conn = DBConfig.getConn();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(stmt);
            ps.setString(1,name);
            ps.setInt(2,24);
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
