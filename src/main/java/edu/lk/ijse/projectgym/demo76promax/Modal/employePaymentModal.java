package edu.lk.ijse.projectgym.demo76promax.Modal;

import edu.lk.ijse.projectgym.demo76promax.Dbconnection.Dbconnection;
import edu.lk.ijse.projectgym.demo76promax.Dtos.employePaymentDto;

import java.sql.*;

public class employePaymentModal {

    // Save employee salary payment
    public boolean saveMethod1(employePaymentDto dto) throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();

        String sql = """
                    INSERT INTO employee_payments
                    (system_user_roll, employee_role, employee_id, payment_date, salary)
                    VALUES (?, ?, ?, ?, ?)
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, dto.getSystem_user_id());
            preparedStatement.setString(2, dto.getEmployee_roll());
            preparedStatement.setString(3, dto.getEmployee_Id());
            preparedStatement.setString(4, dto.getDate());
            preparedStatement.setDouble(5, dto.getSalary());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        }
    }


    public String searchEmployeePaymentsById(String employeeId) throws SQLException, ClassNotFoundException {
        Connection connect = Dbconnection.getObject().getConnection();
        Statement stmt = connect.createStatement();
        String sql = "SELECT\n" +
                "    worker_id AS id,\n" +
                "    worker_name AS name,\n" +
                "    worker_number AS number,\n" +
                "    system_user_Roll AS role,\n" +
                "    email\n" +
                "FROM worker\n" +
                "\n" +
                "UNION ALL\n" +
                "\n" +
                "SELECT\n" +
                "    coach_id AS id,\n" +
                "    coach_name AS name,\n" +
                "    coach_number AS number,\n" +
                "    system_user_Roll AS role,\n" +
                "    email\n" +
                "FROM coaches;";
        ResultSet rsp = stmt.executeQuery(sql);
        String id ="Employee not found";
        while (rsp.next()){

            String system_user_id = rsp.getString("id");

            if(system_user_id.equals(employeeId)){

                id ="Employee avelable :"+ system_user_id;

            }

        }
        return id;

    }


}
