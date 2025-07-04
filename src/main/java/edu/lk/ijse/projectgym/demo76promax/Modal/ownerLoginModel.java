package edu.lk.ijse.projectgym.demo76promax.Modal;

import edu.lk.ijse.projectgym.demo76promax.Dbconnection.Dbconnection;
import edu.lk.ijse.projectgym.demo76promax.Dtos.SystemUser;

import java.sql.*;
import java.util.ArrayList;

public class ownerLoginModel {

    public ownerLoginModel() throws SQLException, ClassNotFoundException { }

    public ArrayList<SystemUser> getuserinformation() throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "SELECT * FROM system_user";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rst = statement.executeQuery();

        ArrayList<SystemUser> userInformation = new ArrayList<>();

        while (rst.next()) {
            SystemUser dto = new SystemUser(
                    rst.getString("user_id"),
                    rst.getString("user_password"),
                    rst.getString("user_role"),
                    rst.getString("user_name"),
                    rst.getString("phone_number")
            );
            userInformation.add(dto);
        }

        return userInformation;
    }

    public static ResultSet getOwnerInformation() throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "SELECT user_password FROM system_user WHERE user_role = 'Admin'";
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }
}
