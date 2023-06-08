package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CandidatBO;
import lk.ijse.dao.custom.*;
import lk.ijse.dao.custom.impl.*;
import lk.ijse.dto.AddCandidateDTO;
import lk.ijse.entity.AddCandidate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CandidatBOImpl implements CandidatBO {
    VoteRegDAO voteRegDAO = new VoteRegDAOImpl();
    CandidateDAO candidateDAO = new CandidateDAOImpl();
    QueryDAO queryDAO = new QueryDAOImpl();
    AddCandidateDAO addCandidateDAO = new AddCandidateDAOImpl();

    @Override
    public boolean deleteVote(String id) throws SQLException, ClassNotFoundException {
        return voteRegDAO.delete(id);
    }

    public List<String> loadElectionId() throws SQLException {
        return candidateDAO.loadElectionID();
    }

    public String getCandidateName(Integer id) throws SQLException {
        return queryDAO.getCandidateName(id);
    }

    public boolean saveCandidate(List<AddCandidateDTO> addCandidateList) throws SQLException {
        List<AddCandidate> addCandidates = new ArrayList<>();
        for (AddCandidateDTO addCandidate : addCandidateList) {
            addCandidates.add(new AddCandidate(addCandidate.getElection_id(),addCandidate.getCandidate_id()));
        }
        return addCandidateDAO.save(addCandidates);
    }

    public boolean updateVote(String id) throws SQLException {
        return voteRegDAO.updateCount(id);
    }
}
