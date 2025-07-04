package edu.lk.ijse.projectgym.demo76promax.Dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {

    private static Dbconnection connectionobject;
    private Connection connection;

    private Dbconnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gymsystemap", "savindu", "gsavindu");

    }


    public static Dbconnection getObject() throws ClassNotFoundException, SQLException {
        if (connectionobject == null || connectionobject.getConnection().isClosed()) {
            connectionobject = new Dbconnection();
        }
        return connectionobject;
    }
    public Connection getConnection() {
        return connection;
    }
}

