package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Division;


import java.sql.SQLException;
import java.util.List;

public interface DivisionDAO extends CrudDAO<Division, String> {

    String getNextOrderId() throws SQLException;

    String splitOrderId(String currentId) ;

    List<String> loadDivisionID() throws SQLException;

    boolean UpdatePopulation(String division_id) throws SQLException;

    boolean UpdateDeadPopulation(String division_id) throws SQLException;

    String getName(String division_id) throws SQLException;

    Integer getPopulation() throws SQLException;
}
