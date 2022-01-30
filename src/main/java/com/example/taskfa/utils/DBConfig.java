package com.example.taskfa.utils;

import java.sql.*;



public class DBConfig {
    private static final String URLDB = "jdbc:mysql://localhost:3306/taskfadb";
    private static final String USERDB = "root";
    private static final String PASSDB = "";

    public static Connection getConn() {
        return conn;
    }

    private static Connection conn = null;

    public static void dbConnect() throws SQLException, ClassNotFoundException {
        try {
            conn =  DriverManager.getConnection(URLDB, USERDB, PASSDB);
        } catch (SQLException e) {
            System.out.println("Connection Failed ! With Database");
            e.printStackTrace();
            throw e;
        }
    }

    public static void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()){
                conn.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    //DB Execute Query Operation
    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            dbConnect();
            System.out.println("Select statement: " + queryStmt + "\n");
            //Create statement
            stmt = conn.createStatement();
            //Execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);
            return resultSet;
            //CachedRowSet Implementation
            //In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            //We are using CachedRowSet
            // crs = new CachedRowSetImpl();
            // crs.populate(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        //Return CachedRowSet
    }
    //DB Execute Update (For Update/Insert/Delete) Operation
    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        //Declare statement as null
        Statement stmt = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            dbConnect();
            //Create Statement
            stmt = conn.createStatement();
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            /*
            if (stmt != null) {
                //Close statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
             */
        }
    }
}
