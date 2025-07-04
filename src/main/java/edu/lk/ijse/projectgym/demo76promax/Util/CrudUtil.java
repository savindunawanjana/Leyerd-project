package edu.lk.ijse.projectgym.demo76promax.Util;

import edu.lk.ijse.projectgym.demo76promax.Dbconnection.Dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {

    public static <T> T execute(String sql, Object... params) throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            pst.setObject(i + 1, params[i]);
        }

        if (sql.trim().toUpperCase().startsWith("SELECT")) {
            ResultSet resultSet = pst.executeQuery();
            return (T) resultSet;
        } else {
            int affectedRows = pst.executeUpdate();
            return (T) (Boolean) (affectedRows > 0);
        }
    }
}