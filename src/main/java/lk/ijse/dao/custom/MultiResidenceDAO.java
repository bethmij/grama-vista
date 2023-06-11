package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.MultiResidence;

import java.sql.SQLException;
import java.util.List;

public interface MultiResidenceDAO extends CrudDAO<MultiResidence,String> {

    boolean save(List<MultiResidence> multiResidenceList) throws SQLException;

    boolean update(List<MultiResidence> multiResidenceList) throws SQLException;

    List<MultiResidence> searchResidence(String reg_id) throws SQLException;
}
