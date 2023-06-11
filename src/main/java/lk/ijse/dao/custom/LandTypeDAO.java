package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.LandDetail;

import java.sql.SQLException;
import java.util.List;

public interface LandTypeDAO extends SuperDAO {
    //Didn't extend crudDAO as this didn't  other crud operations n might be not needed

    Integer getTypeId(String landType) throws SQLException;


}
