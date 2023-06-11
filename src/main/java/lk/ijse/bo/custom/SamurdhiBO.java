package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CivilDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.ResidenceDTO;

import java.sql.SQLException;
import java.util.List;

public interface SamurdhiBO extends SuperBO {

    void saveDetail(DetailDTO detail) throws SQLException;

    List<String> loadResidenceId() throws SQLException;

    List<CivilDTO> getCivilDetail(String id) throws SQLException;

    ResidenceDTO searchResidence(String id) throws SQLException, ClassNotFoundException;
}
