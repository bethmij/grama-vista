package lk.ijse.dao.custom;

import lk.ijse.entity.Disable;


import java.sql.SQLException;
import java.util.List;

public interface DisableDAO {
    Integer getNextId() throws SQLException;

    boolean save(Disable disable) throws SQLException;

    List<String> loadDisableId() throws SQLException;



    boolean delete(String id) throws SQLException;


    boolean update(Disable disable) throws SQLException;
}
