package edu.lk.ijse.projectgym.demo76promax.dao.custom;

import java.sql.SQLException;

public interface CoachDAO {
    String findById(String coach1) throws SQLException, ClassNotFoundException;
}
