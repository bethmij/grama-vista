package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.AddCandidateDAO;
import lk.ijse.entity.AddCandidate;
import lk.ijse.dao.custom.impl.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class AddCandidateDAOImpl implements AddCandidateDAO {

    @Override
    public boolean save(List<AddCandidate> addCandidateList) throws SQLException {
        for(AddCandidate list : addCandidateList) {
            if(!save(list)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean save(AddCandidate addCandidate) throws SQLException {

        return CrudUtil.execute("INSERT INTO grama_vista.add_candidate (election_id, candidate_id) VALUES (?,?)",
                addCandidate.getElection_id(), addCandidate.getCandidate_id());
    }


    @Override
    public  boolean deleteCandidate(String id) throws SQLException {
        return CrudUtil.execute("DELETE  FROM grama_vista.add_candidate WHERE election_id=?", id);
    }
}
