package lk.ijse.bo.custom;

import lk.ijse.dto.CivilDTO;
import lk.ijse.dto.ContactDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.MultiResidenceDTO;

import java.sql.SQLException;
import java.util.List;

public interface CivilManageBO {
    List<String> loadCivilId() throws SQLException;

    List<CivilDTO> searchAllCivil() throws SQLException;

    CivilDTO searchCivil(String id) throws SQLException, ClassNotFoundException;

    List<ContactDTO> searchContact(String id) throws SQLException;

    List<MultiResidenceDTO> searchResidence(String id) throws SQLException;

    String getCivilName(String id) throws SQLException;

    void saveDetail(DetailDTO detail) throws SQLException;
}
