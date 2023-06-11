package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.AddCandidate;

import java.sql.SQLException;
import java.util.List;

public interface AddCandidateDAO extends CrudDAO<AddCandidate, String> {

    boolean save(List<AddCandidate> addCandidateList) throws SQLException;
}
