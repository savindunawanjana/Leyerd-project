package edu.lk.ijse.projectgym.demo76promax.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    List<T> getAll() throws ClassNotFoundException, SQLException;
    Boolean delete(String id)throws  ClassNotFoundException, SQLException;
    Boolean update(T t)throws  ClassNotFoundException, SQLException;
    Boolean save(T t)throws  ClassNotFoundException, SQLException;
    String getNextId()throws  ClassNotFoundException, SQLException;
    String findNameById(String Id)  throws  ClassNotFoundException, SQLException;

}
