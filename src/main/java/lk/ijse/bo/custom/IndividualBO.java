package lk.ijse.bo.custom;

import lk.ijse.dto.DetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface IndividualBO {

    List<String> loadResidenceId() throws SQLException;

    String getCivilNextId() throws SQLException;

    void saveDetail(DetailDTO detail) throws SQLException;
}
