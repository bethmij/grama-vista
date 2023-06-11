package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.DisableDTO;

import java.sql.SQLException;
import java.util.List;

public interface DisableRegistrationBO extends SuperBO {
    List<String> loadCivilId() throws SQLException;

    Integer getNextDisableId() throws SQLException;

    boolean saveDisable(DisableDTO disableDTO) throws SQLException, ClassNotFoundException;

    void saveDetail(DetailDTO detail) throws SQLException;

    boolean updateDisable(DisableDTO disableDTO) throws SQLException, ClassNotFoundException;

    String searchCivilByID (String id) throws SQLException;
}
