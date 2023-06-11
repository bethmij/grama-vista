package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

import java.sql.SQLException;

public interface AddLandTypeBO extends SuperBO {
    Integer getLandTypeID(String id) throws SQLException;
}
