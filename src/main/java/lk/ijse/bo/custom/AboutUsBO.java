package lk.ijse.bo.custom;

import lk.ijse.dto.CivilDTO;
import lk.ijse.dto.DetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface AboutUsBO {
    List<CivilDTO> searchCivil() throws SQLException;

    boolean saveDetail(DetailDTO detail) throws SQLException;

    Integer getPopulation() throws SQLException;

    Integer getMale() throws SQLException;

    Integer getFemale() throws SQLException;

    Integer getResidenceCount() throws SQLException;

    Integer getLandCount() throws SQLException;
}
