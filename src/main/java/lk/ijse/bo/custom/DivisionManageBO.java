package lk.ijse.bo.custom;

import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.DivisionDTO;

import java.sql.SQLException;
import java.util.List;

public interface DivisionManageBO {

    List<String> loadDivision() throws SQLException;

    List<DivisionDTO> searchAllDivision() throws SQLException;

    DivisionDTO searchDivision(String id) throws SQLException;

    boolean deleteDivision(String id) throws SQLException;

    String getDivisionName(String id) throws SQLException;

    void saveDetail(DetailDTO detail) throws SQLException;

}
