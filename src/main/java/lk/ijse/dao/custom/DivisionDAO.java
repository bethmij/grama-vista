package lk.ijse.dao.custom;

import lk.ijse.entity.Division;


import java.sql.SQLException;
import java.util.List;

public interface DivisionDAO {
    String getNextOrderId() throws SQLException;

    String splitOrderId(String currentId) ;

    boolean save(Division division) throws SQLException;

    List<String> loadDivisionID() throws SQLException;

    boolean UpdatePopulation(String division_id) throws SQLException;

    boolean UpdateDeadPopulation(String division_id) throws SQLException;

    String getName(String division_id) throws SQLException;

    Division search(String division_id) throws SQLException;

    boolean dead(String division_id) throws SQLException;

    List<Division> searchAll() throws SQLException;

    boolean update(Division division) throws SQLException;

    Integer getPopulation() throws SQLException;
}
