package lk.ijse.bo.custom;

import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.DisableDTO;

import java.sql.SQLException;
import java.util.List;

public interface DisableManageBO {
    List<String> loadDisableId() throws SQLException;

    List<DisableDTO> searchAllDisable() throws SQLException;

    DisableDTO searchDisable(String id) throws SQLException;

    boolean deleteDisable(String id) throws SQLException;

    void saveDetail(DetailDTO detail) throws SQLException;
}
