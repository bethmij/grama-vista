package lk.ijse.dao.custom;

import lk.ijse.entity.Maternity;


import java.sql.SQLException;
import java.util.List;

public interface MaternityDAO {
    Integer getNextId() throws SQLException;

    boolean save(Maternity maternity) throws SQLException;

    List<String> loadMaternityID() throws SQLException;


    boolean dead(String id) throws SQLException;


    boolean update(Maternity maternity) throws SQLException;
}
