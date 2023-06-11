package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.MaternityDTO;

import java.sql.SQLException;
import java.util.List;

public interface MaternityRegistBO extends SuperBO {

    List<String> loadCivilId() throws SQLException;

    void saveDetail(DetailDTO detail) throws SQLException;

    Integer getNextMaternityID() throws SQLException;

    boolean saveMaternity(MaternityDTO maternityDTO) throws SQLException, ClassNotFoundException;

    boolean updateMaternity(MaternityDTO maternityDTO) throws SQLException, ClassNotFoundException;

    String searchCivilByID (String id) throws SQLException;
}
