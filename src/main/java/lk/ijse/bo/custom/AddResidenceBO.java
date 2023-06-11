package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface AddResidenceBO extends SuperBO {
    List<String> loadResidenceID() throws SQLException;
}
