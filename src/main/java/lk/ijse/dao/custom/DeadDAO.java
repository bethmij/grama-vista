package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Dead;


import java.sql.SQLException;
import java.util.List;

public interface DeadDAO extends CrudDAO<Dead,String> {
    Integer getNextId() throws SQLException;



    List<String> loadDeadId() throws SQLException;




}

