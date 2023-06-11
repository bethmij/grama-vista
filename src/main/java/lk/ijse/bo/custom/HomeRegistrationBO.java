package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.ResidenceDTO;

import java.sql.SQLException;
import java.util.List;

public interface HomeRegistrationBO extends SuperBO {

    List<String> loadDivisionId() throws SQLException;

    void saveDetail(DetailDTO detail) throws SQLException;

    boolean updateResidence(ResidenceDTO residenceDTO) throws SQLException, ClassNotFoundException;

    boolean saveResidence(ResidenceDTO residenceDTO) throws SQLException, ClassNotFoundException;
}
