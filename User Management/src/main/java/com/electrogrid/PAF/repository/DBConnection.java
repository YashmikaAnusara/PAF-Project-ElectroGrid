package com.electrogrid.PAF.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    final String URL = System.getenv("JDBC_DATABASE_URL") != null ? System.getenv("JDBC_DATABASE_URL") : "jdbc:mysql://localhost:3306/electroGridDB";
    final String USER = System.getenv("JDBC_DATABASE_USERNAME") != null ? System.getenv("JDBC_DATABASE_USERNAME") : "root";
    final String PASSWORD = System.getenv("JDBC_DATABASE_PASSWORD") != null ? System.getenv("JDBC_DATABASE_PASSWORD") : "";

    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

}
