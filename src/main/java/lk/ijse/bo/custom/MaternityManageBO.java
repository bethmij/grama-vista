package lk.ijse.bo.custom;

import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.MaternityDTO;

import java.sql.SQLException;
import java.util.List;

public interface MaternityManageBO {

    void saveDetail(DetailDTO detail) throws SQLException;

    List<String> loadMaternityID() throws SQLException;

    List<MaternityDTO> searchAllMaternity() throws SQLException;

    MaternityDTO searchMaternity(String id) throws SQLException;

    boolean deleteMaternity(String id) throws SQLException;
}
