package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.DeadDTO;
import lk.ijse.dto.DetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface DeadPeopleBO extends SuperBO {

    Integer getNextDeadId() throws SQLException;

    List<String> loadCivilId() throws SQLException;

    String getDivisionId(Integer id) throws SQLException;

    boolean saveDead(DeadDTO deadDTO) throws SQLException, ClassNotFoundException;

    void saveDetail(DetailDTO detail) throws SQLException;

    boolean updateDead(DeadDTO deadDTO) throws SQLException, ClassNotFoundException;

    String searchCivilByID (String id) throws SQLException;
}
