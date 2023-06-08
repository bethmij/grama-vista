package lk.ijse.bo.custom;

import java.sql.SQLException;

public interface CivilViewBO {
    boolean deleteCivil(String id) throws SQLException, ClassNotFoundException;
}
