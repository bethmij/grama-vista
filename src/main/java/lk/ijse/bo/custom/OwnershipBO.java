package lk.ijse.bo.custom;

import java.sql.SQLException;
import java.util.List;

public interface OwnershipBO {

    List<String> loadCivilId() throws SQLException;
}
