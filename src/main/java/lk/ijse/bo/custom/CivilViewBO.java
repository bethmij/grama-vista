package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

import java.sql.SQLException;

public interface CivilViewBO extends SuperBO  {
    boolean deleteCivil(String id) throws SQLException, ClassNotFoundException;
}
