package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Detail;

import java.sql.SQLException;
import java.util.List;

public interface DetailDAO extends SuperDAO {
    //Didn't extend crudDAO as this didn't use other crud operations n might be not needed

    boolean save(Detail details) throws SQLException;

    List<Detail> search(Integer dayCount) throws SQLException;

    List<Detail> searchAll() throws SQLException;
}
