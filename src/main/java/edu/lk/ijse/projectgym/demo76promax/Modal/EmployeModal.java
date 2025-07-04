package edu.lk.ijse.projectgym.demo76promax.Modal;

import edu.lk.ijse.projectgym.demo76promax.Dbconnection.Dbconnection;
import edu.lk.ijse.projectgym.demo76promax.Dtos.EmployeDto;
import edu.lk.ijse.projectgym.demo76promax.Util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class EmployeModal {

    // Save worker (cleaner, receptionist, etc.)
    public String saveWorker(EmployeDto dto) throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "INSERT INTO worker(worker_id, worker_name, worker_number, system_user_Roll,add_date, email) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, dto.getEmployeeId());
        stmt.setString(2, dto.getName());
        stmt.setString(3, dto.getCnumber());
        stmt.setString(4, dto.getSystemUserId());
        stmt.setString(5, dto.getDate());
        stmt.setString(6, dto.getEmail());

        int result = stmt.executeUpdate();
        return result > 0 ? "Saved successfully" : "Not saved";
    }

    // Save coach
    public String saveCoach(EmployeDto dto) throws SQLException, ClassNotFoundException {
        try {


            Connection connection = Dbconnection.getObject().getConnection();
            String sql = "INSERT INTO coaches(coach_id, coach_name, coach_number, system_user_Roll,add_date,email) VALUES (?, ?, ?, ?, ?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, dto.getEmployeeId());
            stmt.setString(2, dto.getName());
            stmt.setString(3, dto.getCnumber());
            stmt.setString(4, dto.getSystemUserId());
            stmt.setString(5, dto.getDate());
            stmt.setString(6, dto.getEmail());

            int result = stmt.executeUpdate();
            return result > 0 ? "Saved successfully" : "Not saved";
        }catch (SQLException e){
            e.printStackTrace();
            return e.getMessage();

        }
    }

    // Delete worker
    public String deleteWorker(String workerId) throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "DELETE FROM worker WHERE worker_id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, workerId);
        int result = stmt.executeUpdate();
        return result > 0 ? "Deleted successfully" : "No matching worker to delete";
    }

    // Delete coach
    public String deleteCoach(String coachId) throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "DELETE FROM coaches WHERE coach_id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, coachId);
        int result = stmt.executeUpdate();
        return result > 0 ? "Deleted successfully" : "No matching coach to delete";
    }

    // Get all coaches
    public ObservableList<EmployeDto> getAllCoaches() throws SQLException, ClassNotFoundException {
        ObservableList<EmployeDto> list = FXCollections.observableArrayList();
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "SELECT * FROM coaches";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            list.add(new EmployeDto(
                    rs.getString("coach_id"),
                    rs.getString("coach_name"),
                    rs.getString("coach_number"),
                    rs.getString("system_user_Roll"),
                    rs.getString("add_date"),
                    rs.getString("email")
            ));
        }
        return list;
    }

    // Get all workers (cleaners, receptionists, etc.)
    public ObservableList<EmployeDto> getAllWorkers() throws SQLException, ClassNotFoundException {
        ObservableList<EmployeDto> list = FXCollections.observableArrayList();
        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "SELECT * FROM worker";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            list.add(new EmployeDto(
                    rs.getString("worker_id"),
                    rs.getString("worker_name"),
                    rs.getString("worker_number"),
                    rs.getString("system_user_Roll"),
                    rs.getString("add_date"),
                    rs.getString("email")
            ));
        }
        return list;
    }


    public int setlableCoachCount() throws SQLException, ClassNotFoundException {

        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "SELECT COUNT(*) AS employee_count FROM coaches";
        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return resultSet.getInt("employee_count");
        }
        return 0;


    }

    public int setlableCleanerCount() throws SQLException, ClassNotFoundException {

        Connection connection = Dbconnection.getObject().getConnection();
        String sql = "SELECT COUNT(*) AS employee_count FROM worker";
       // String sql = "SELECT COUNT(*) AS employee_count FROM coaches";
        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return resultSet.getInt("employee_count");
        }
        return 0;


    }

    public String findNameById(String id) throws SQLException, ClassNotFoundException {
        String name = "";

        String sql = "SELECT customer_name FROM customer WHERE customer_id = ?";

        try (
                Connection connection = Dbconnection.getObject().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
            stmt.setString(1, id);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    name = resultSet.getString("name");
                }
            }
        }

        return name;
    }

    public String findNameByIds(String id) throws SQLException, ClassNotFoundException {
        String name = "";

        String sql = "SELECT worker_name FROM worker WHERE worker_id = ?";

        try (
                Connection connection = Dbconnection.getObject().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
            stmt.setString(1, id);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    name = resultSet.getString("worker_name");
                }
            }
        }

        return name;
    }



}
