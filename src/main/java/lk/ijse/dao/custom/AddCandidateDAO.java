package lk.ijse.dao.custom;

import lk.ijse.entity.AddCandidate;

import java.sql.SQLException;
import java.util.List;

public interface AddCandidateDAO {
    //Didn't extend crudDAO as this didn't use another method

    boolean save(List<AddCandidate> addCandidateList) throws SQLException;

    boolean save(AddCandidate addCandidate) throws SQLException;

    boolean deleteCandidate(String id) throws SQLException;
}
