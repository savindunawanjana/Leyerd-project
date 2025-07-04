package edu.lk.ijse.projectgym.demo76promax.Modal;

import edu.lk.ijse.projectgym.demo76promax.Dbconnection.Dbconnection;
import edu.lk.ijse.projectgym.demo76promax.Dtos.DeleteClenerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DeleteClenerModel {

    public boolean saveDeleteCleaner(DeleteClenerDto dto) throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getObject().getConnection();

        String sql = "INSERT INTO delete_worker(worker_id,worker_name,delete_date,system_user_Roll,reason_to_delete) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1, dto.getWorkerId());
        stmt.setString(2, dto.getWorkname());
        stmt.setString(3, dto.getDate());
        stmt.setString(4, dto.getUserRoller());
        stmt.setString(5, dto.getResonTodelete());

        int rowsAffected = stmt.executeUpdate();

        return rowsAffected > 0;
    }



}
