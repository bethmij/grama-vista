package lk.ijse.dao.custom;

import lk.ijse.entity.LandDetail;

import java.sql.SQLException;
import java.util.List;

public interface LandTypeDAO {

    Integer getTypeId(String landType) throws SQLException;


}
