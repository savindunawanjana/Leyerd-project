package edu.lk.ijse.projectgym.demo76promax.dao.custom.impl;

import edu.lk.ijse.projectgym.demo76promax.dao.custom.additionalLeaveCoachDAO;
import edu.lk.ijse.projectgym.demo76promax.entity.AdditionalLeaveCoach;

import java.sql.SQLException;
import java.util.List;

public class additionalLeaveCoachDAOImpl implements additionalLeaveCoachDAO {
    @Override
    public List<AdditionalLeaveCoach> getAll() throws ClassNotFoundException, SQLException {
        return List.of();
    }

    @Override
    public Boolean delete(String id) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public Boolean update(AdditionalLeaveCoach additionalLeaveCoach) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public Boolean save(AdditionalLeaveCoach additionalLeaveCoach) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public String getNextId() throws ClassNotFoundException, SQLException {
        return "";
    }

    @Override
    public String findNameById(String Id) throws ClassNotFoundException, SQLException {
        return "";
    }
}
