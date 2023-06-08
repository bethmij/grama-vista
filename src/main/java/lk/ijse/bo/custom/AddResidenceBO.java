package lk.ijse.bo.custom;

import java.sql.SQLException;
import java.util.List;

public interface AddResidenceBO {
    List<String> loadResidenceID() throws SQLException;
}
