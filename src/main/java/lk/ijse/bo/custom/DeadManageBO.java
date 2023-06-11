package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.DeadDTO;
import lk.ijse.dto.DetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface DeadManageBO extends SuperBO {
    List<DeadDTO> searchAllDead() throws SQLException;

    DeadDTO searchDeath(String id) throws SQLException;

    boolean deleteDead(String id) throws SQLException, ClassNotFoundException;

    void saveDetail(DetailDTO detail) throws SQLException;

    List<String> loadDeadId() throws SQLException;
}
