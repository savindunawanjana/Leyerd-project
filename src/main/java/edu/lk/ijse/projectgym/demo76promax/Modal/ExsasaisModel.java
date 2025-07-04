package edu.lk.ijse.projectgym.demo76promax.Modal;

import edu.lk.ijse.projectgym.demo76promax.Dbconnection.Dbconnection;
import edu.lk.ijse.projectgym.demo76promax.Dtos.ExsaisDto;
import edu.lk.ijse.projectgym.demo76promax.Dtos.tm.ExsaisTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExsasaisModel {

    private Connection connection = Dbconnection.getObject().getConnection();

    public ExsasaisModel() throws SQLException, ClassNotFoundException {

    }

    public String saveMethod(ExsaisDto dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO manage_exercises(id, name, category, system_user_id) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, dto.getId());
        preparedStatement.setString(2, dto.getExsaisName());
        preparedStatement.setString(3, dto.getExsaisCategory());
        preparedStatement.setString(4, dto.getSystemUserid());  // <-- use systemUserid here!

        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            return "Successfully saved";
        } else {
            return "Failed to save";
        }
    }


    public String deleteMethod(String Id) throws SQLException, ClassNotFoundException {

        connection = Dbconnection.getObject().getConnection();
        String sql = "DELETE FROM manage_exercises WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, Id);
        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            return "Successfuly delete ";
        } else {
            return "Failed delete ";
        }

    }

    public String updateMethod(ExsaisDto dto) throws SQLException, ClassNotFoundException {
        connection = Dbconnection.getObject().getConnection();
        String sql = "UPDATE manage_exercises SET  name=?,category=? WHERE id=? ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, dto.getExsaisName());
        preparedStatement.setString(2, dto.getExsaisCategory());

        preparedStatement.setInt(3, dto.getId());
        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            return "Successfuly update ";
        } else {
            return "Failed update ";
        }
    }

    public ArrayList<ExsaisTm> getAllmethod() throws SQLException, ClassNotFoundException {

        ArrayList<ExsaisTm> list = new ArrayList();
        connection = Dbconnection.getObject().getConnection();
        String sql = "SELECT * FROM manage_exercises";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        ExsaisTm exsaistm;

        while (resultSet.next()) {
            exsaistm = new ExsaisTm(

                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("category"),
                    resultSet.getString("added_date"),
                    resultSet.getString("system_user_id")
            );

            list.add(exsaistm);
        }

        return list;
    }


}
