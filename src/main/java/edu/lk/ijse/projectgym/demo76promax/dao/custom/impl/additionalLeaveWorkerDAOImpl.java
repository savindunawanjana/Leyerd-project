package edu.lk.ijse.projectgym.demo76promax.dao.custom.impl;

import edu.lk.ijse.projectgym.demo76promax.dao.custom.additionalLeaveWorkerDAO;
import edu.lk.ijse.projectgym.demo76promax.entity.AdditionalLeaveWorker;

import java.sql.SQLException;
import java.util.List;

public class additionalLeaveWorkerDAOImpl implements additionalLeaveWorkerDAO {
    @Override
    public List<AdditionalLeaveWorker> getAll() throws ClassNotFoundException, SQLException {
        return List.of();
    }

    @Override
    public Boolean delete(String id) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public Boolean update(AdditionalLeaveWorker additionalLeaveWorker) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public Boolean save(AdditionalLeaveWorker additionalLeaveWorker) throws ClassNotFoundException, SQLException {
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
