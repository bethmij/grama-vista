package lk.ijse.bo.custom;

import lk.ijse.dto.CivilDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.ResidenceDTO;

import java.sql.SQLException;
import java.util.List;

public interface HomeManageBO {

    List<String> loadResidenceId() throws SQLException;

    List<ResidenceDTO>  searchAllResidence() throws SQLException;

    ResidenceDTO searchResidence(String id) throws SQLException, ClassNotFoundException;

    List<CivilDTO> getCivilDetail(String id) throws SQLException;

    boolean deleteResidence(String id) throws SQLException, ClassNotFoundException;

    void saveDetail(DetailDTO detail) throws SQLException;
}
