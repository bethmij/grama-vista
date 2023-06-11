package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface OwnershipBO extends SuperBO {

    List<String> loadCivilId() throws SQLException;
}
