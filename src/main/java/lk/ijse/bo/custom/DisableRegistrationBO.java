package lk.ijse.bo.custom;

import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.DisableDTO;

import java.sql.SQLException;
import java.util.List;

public interface DisableRegistrationBO {
    List<String> loadCivilId() throws SQLException;

    Integer getNextDisableId() throws SQLException;

    boolean saveDisable(DisableDTO disableDTO) throws SQLException;

    void saveDetail(DetailDTO detail) throws SQLException;

    boolean updateDisable(DisableDTO disableDTO) throws SQLException;

    String searchCivilByID (String id) throws SQLException;
}
