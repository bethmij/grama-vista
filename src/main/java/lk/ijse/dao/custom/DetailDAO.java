package lk.ijse.dao.custom;

import lk.ijse.entity.Detail;

import java.sql.SQLException;
import java.util.List;

public interface DetailDAO {
    boolean save(Detail details) throws SQLException;

    List<Detail> search(Integer dayCount) throws SQLException;



    List<Detail> searchAll() throws SQLException;
}
