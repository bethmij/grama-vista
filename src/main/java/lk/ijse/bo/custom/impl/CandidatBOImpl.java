package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CandidatBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.*;
import lk.ijse.dao.custom.impl.*;
import lk.ijse.dto.AddCandidateDTO;
import lk.ijse.entity.AddCandidate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CandidatBOImpl implements CandidatBO {

    VoteRegDAO voteRegDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.VOTEREGDAO);
    CandidateDAO candidateDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CANDIDATEDAO);
    QueryDAO queryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);
    AddCandidateDAO addCandidateDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ADDCANDIDATEDAO);

    @Override
    public boolean deleteVote(String id) throws SQLException, ClassNotFoundException {
        return voteRegDAO.delete(id);
    }

    @Override
    public List<String> loadElectionId() throws SQLException {
        return candidateDAO.loadElectionID();
    }

    @Override
    public String getCandidateName(Integer id) throws SQLException {
        return queryDAO.getCandidateName(id);
    }

    @Override
    public boolean saveCandidate(List<AddCandidateDTO> addCandidateList) throws SQLException {
        List<AddCandidate> addCandidates = new ArrayList<>();
        for (AddCandidateDTO addCandidate : addCandidateList) {
            addCandidates.add(new AddCandidate(addCandidate.getElection_id(),addCandidate.getCandidate_id()));
        }
        return addCandidateDAO.save(addCandidates);
    }

    @Override
    public boolean updateVote(String id) throws SQLException {
        return voteRegDAO.updateCount(id);
    }
}
