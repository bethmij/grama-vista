package lk.ijse.bo.custom;

import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.MaternityDTO;

import java.sql.SQLException;
import java.util.List;

public interface MaternityRegistBO {

    List<String> loadCivilId() throws SQLException;

    void saveDetail(DetailDTO detail) throws SQLException;

    Integer getNextMaternityID() throws SQLException;

    boolean saveMaternity(MaternityDTO maternityDTO) throws SQLException;

    boolean updateMaternity(MaternityDTO maternityDTO) throws SQLException;

    String searchCivilByID (String id) throws SQLException;
}
