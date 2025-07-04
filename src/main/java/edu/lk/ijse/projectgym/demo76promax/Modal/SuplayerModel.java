package edu.lk.ijse.projectgym.demo76promax.Modal;

import edu.lk.ijse.projectgym.demo76promax.Dbconnection.Dbconnection;
import edu.lk.ijse.projectgym.demo76promax.Dtos.SuplayerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SuplayerModel {

    public String saveSuplayer(SuplayerDto dto) throws SQLException, ClassNotFoundException {

        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "INSERT INTO suppliers VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, dto.getSupplier_id());
        preparedStatement.setString(2, dto.getSupplier_name());
        preparedStatement.setString(3, dto.getPone_number());
        preparedStatement.setString(4, dto.getEmail());
        preparedStatement.setString(5, dto.getAddress());

        int i = preparedStatement.executeUpdate();
        if (i > 0) {

            return " Save successfully ";

        } else {
            return " Save failed ";
        }

    }

    public ArrayList<SuplayerDto>  getAll() throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "SELECT ALL  * FROM suppliers";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<SuplayerDto> list = new ArrayList<>();
        SuplayerDto dto ;
        while (resultSet.next()){
            dto = new SuplayerDto(
                    resultSet.getString("supplier_id"),
                    resultSet.getString("name"),
                    resultSet.getString("phone"),
                    resultSet.getString("email"),
                    resultSet.getString("address")
            );
            list.add(dto);
        }
        return list;

    }

    public String  deleteSuplayer(String id) throws SQLException, ClassNotFoundException {

        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "DELETE FROM suppliers WHERE supplier_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id);
        int i = preparedStatement.executeUpdate();
        if (i > 0) {
  return  "Delete successfully ";
        } else return "Delete failed ";
    }

    public String  updateSuplayer(SuplayerDto dto) throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "UPDATE suppliers SET name =?,phone = ?,email = ?,address = ? WHERE supplier_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, dto.getSupplier_name());
        preparedStatement.setString(2, dto.getPone_number());
        preparedStatement.setString(3, dto.getEmail());
        preparedStatement.setString(4, dto.getAddress());
        preparedStatement.setString(5, dto.getSupplier_id());
        int i = preparedStatement.executeUpdate();
        if(i>0){
            return "Update successfully ";
        } else return "Update failed ";

    }
}
