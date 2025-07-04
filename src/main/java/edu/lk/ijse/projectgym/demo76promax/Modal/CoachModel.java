package edu.lk.ijse.projectgym.demo76promax.Modal;

import edu.lk.ijse.projectgym.demo76promax.Dbconnection.Dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoachModel {
    public String findById(String coach1) throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "select coach_name from coaches where coach_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, coach1);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {

            return resultSet.getString("coach_name");

        }

     return  null;


     }

}
