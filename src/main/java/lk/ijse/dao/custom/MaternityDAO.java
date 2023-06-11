package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Maternity;


import java.sql.SQLException;
import java.util.List;

public interface MaternityDAO extends CrudDAO<Maternity, String > {

    Integer getNextId() throws SQLException;

    List<String> loadMaternityID() throws SQLException;

}
