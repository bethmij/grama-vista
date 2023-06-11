package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.DetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface IndividualBO extends SuperBO {

    List<String> loadResidenceId() throws SQLException;

    String getCivilNextId() throws SQLException;

    void saveDetail(DetailDTO detail) throws SQLException;
}
