package edu.lk.ijse.projectgym.demo76promax.dao.custom.impl;

import edu.lk.ijse.projectgym.demo76promax.dao.custom.CoachDAO;
import edu.lk.ijse.projectgym.demo76promax.dao.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CoachDAOImpl implements CoachDAO {
    @Override
    public String findById(String coach1) throws SQLException, ClassNotFoundException {
        String sql = "SELECT coach_name FROM coaches WHERE coach_id=?";
        ResultSet resultSet = SQLUtil.execute(sql, coach1);

        if (resultSet.next()) {
            return resultSet.getString("coach_name");
        }
        return null;
    }
}
