package edu.lk.ijse.projectgym.demo76promax.Modal;

import edu.lk.ijse.projectgym.demo76promax.Dbconnection.Dbconnection;
import edu.lk.ijse.projectgym.demo76promax.Dtos.EmployeeDataDto;
import edu.lk.ijse.projectgym.demo76promax.dao.util.SQLUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class EmployeedataModel {


    public ObservableList<EmployeeDataDto> getallMethod() throws SQLException, ClassNotFoundException {


        ObservableList<EmployeeDataDto> oblist = FXCollections.observableArrayList();
        Connection connection = Dbconnection.getObject().getConnection();
        EmployeeDataDto employeeDataDto;
        String sql = "SELECT ALL * FROM  employees_table";

        ResultSet resultSet = SQLUtil.execute(sql);

        while (resultSet.next()) {
            employeeDataDto = new EmployeeDataDto(

                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    resultSet.getString("number"),
                    resultSet.getString("email"),
                    resultSet.getString("role")
            );
            oblist.add(employeeDataDto);
        }
        return oblist;
    }

    public Boolean shouldBeRunThisMethod() throws SQLException, ClassNotFoundException {

        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "INSERT INTO employees_table (id, name, number, role, email)\n" +
                "SELECT w.worker_id, w.worker_name, w.worker_number, w.system_user_Roll, w.email\n" +
                "FROM worker w\n" +
                "WHERE NOT EXISTS (\n" +
                "    SELECT 1 FROM employees_table e WHERE e.id = w.worker_id\n" +
                ")\n" +
                "\n" +
                "UNION ALL\n" +
                "\n" +
                "SELECT c.coach_id, c.coach_name, c.coach_number, c.system_user_Roll, c.email\n" +
                "FROM coaches c\n" +
                "WHERE NOT EXISTS (\n" +
                "    SELECT 1 FROM employees_table e WHERE e.id = c.coach_id\n" +
                ")";

        Statement statement = connection.createStatement();
        int rowsInserted = statement.executeUpdate(sql);
        System.out.println("rowsInserted = " + rowsInserted);
//connection.rollback();
        return rowsInserted > 0 ? true : false;

    }


    public ObservableList<EmployeeDataDto> SerchMethod(String id1, String id2) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM employees_table WHERE id IN (?, ?)";

        Connection connection = Dbconnection.getObject().getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);

        pst.setString(1, id1);
        pst.setString(2, id2);

        ResultSet rs = pst.executeQuery();

        ObservableList<EmployeeDataDto> dtoList = FXCollections.observableArrayList();

        while (rs.next()) {
            EmployeeDataDto dto = new EmployeeDataDto(
                    rs.getString("id"),
                    rs.getString("role"),
                    rs.getString("name")
            );
            dtoList.add(dto);
        }

        return dtoList;
    }


    public EmployeeDataDto findById(String id) throws SQLException, ClassNotFoundException {

        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "SELECT * FROM employees_table WHERE id = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, id);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            EmployeeDataDto employeeDataDto = new EmployeeDataDto(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("number"),
                    rs.getString("role"),
                    rs.getString("email")
            );
            return employeeDataDto;
        }

        return null;
    }





}
