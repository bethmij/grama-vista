package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Disable;


import java.sql.SQLException;
import java.util.List;

public interface DisableDAO extends CrudDAO<Disable, String> {

    Integer getNextId() throws SQLException;

    List<String> loadDisableId() throws SQLException;

}
