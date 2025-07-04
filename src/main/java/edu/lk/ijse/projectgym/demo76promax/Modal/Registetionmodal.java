package edu.lk.ijse.projectgym.demo76promax.Modal;

import edu.lk.ijse.projectgym.demo76promax.Dbconnection.Dbconnection;
import edu.lk.ijse.projectgym.demo76promax.Dtos.SystemUser;
import edu.lk.ijse.projectgym.demo76promax.dao.util.SQLUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class Registetionmodal {

    public String savenewMember(SystemUser systemUser) throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "INSERT INTO system_user VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, systemUser.getUser_Id());
        preparedStatement.setString(2, systemUser.getUser_password());
        preparedStatement.setString(3, systemUser.getUser_Roll());
        preparedStatement.setString(4, systemUser.getUser_Name());
        preparedStatement.setString(5, systemUser.getPon_number());

        int result = preparedStatement.executeUpdate();
        return result > 0 ? "save successfully" : "not saved";
    }

    public ObservableList<SystemUser> getAllmethod() throws SQLException, ClassNotFoundException {
        ObservableList<SystemUser> users = FXCollections.observableArrayList();
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "SELECT * FROM system_user";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            users.add(new SystemUser(
                    rs.getString("user_id"),
                    rs.getString("user_password"),
                    rs.getString("user_role"),
                    rs.getString("user_name"),
                    rs.getString("phone_number")
            ));
        }
        return users;
    }

    public boolean deleteMember(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM system_user WHERE user_id = ?";
        return SQLUtil.execute(sql, id);
    }

    public boolean updateuserMethod(SystemUser object) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE system_user SET user_password = ?, user_role = ?, user_name = ?, phone_number = ? WHERE user_id = ?";
        return SQLUtil.execute(sql,
                object.getUser_password(),
                object.getUser_Roll(),
                object.getUser_Name(),
                object.getPon_number(),
                object.getUser_Id());
    }

    public String getuserRollmethod(String user_password) throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "SELECT user_role FROM system_user WHERE user_password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user_password);
        ResultSet rs = preparedStatement.executeQuery();

        String role = null;
        if (rs.next()) {
            role = rs.getString("user_role");
        }
        return role;
    }

    public String getuserId(String user_password) throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "SELECT user_id FROM system_user WHERE user_password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user_password);
        ResultSet rs = preparedStatement.executeQuery();

        String id = null;
        if (rs.next()) {
            id = rs.getString("user_id");
        }
        return id;
    }

    public String getNextId() throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "SELECT user_id FROM system_user ORDER BY user_id DESC LIMIT 1";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet resultSet = pst.executeQuery();

        char prefix = 'U';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String numericPart = lastId.substring(1);
            int nextIdNum = Integer.parseInt(numericPart) + 1;
            return String.format(prefix + "%03d", nextIdNum);
        }
        return prefix + "001";
    }



    public boolean isUserIdExists(String id) throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "SELECT user_id FROM system_user WHERE user_id = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, id);
        ResultSet resultSet = pst.executeQuery();
        return resultSet.next();
    }
}
