package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.VoteBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.*;
import lk.ijse.dao.custom.impl.*;
import lk.ijse.dao.custom.impl.util.CrudUtil;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.AddCandidateDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.VoteDTO;
import lk.ijse.entity.AddCandidate;
import lk.ijse.entity.Detail;
import lk.ijse.entity.VoteReg;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VoteBOImpl implements VoteBO
{
    CandidateDAO candidateDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CANDIDATEDAO);
    DetailDAO detailDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DETAILDAO);
    VoteRegDAO voteRegDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.VOTEREGDAO);
    QueryDAO queryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);
    AddCandidateDAO addCandidateDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ADDCANDIDATEDAO);

    @Override
    public List<String> loadElectionID() throws SQLException {
       return candidateDAO.loadElectionID();
    }

    @Override
    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }

    @Override
    public boolean saveVote(VoteDTO voteDTO, List<AddCandidateDTO> addCandidateList) throws SQLException {
        VoteReg voteReg = new VoteReg(voteDTO.getElection_id(),voteDTO.getElection_type(),voteDTO.getCandidate_count(),voteDTO.getDate());

        List<AddCandidate> addCandidates = new ArrayList<>();
        for (AddCandidateDTO addCandidateDTO : addCandidateList) {
            addCandidates.add(new AddCandidate(addCandidateDTO.getElection_id(),addCandidateDTO.getCandidate_id()));
        }
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = CrudUtil.execute("INSERT INTO grama_vista.votereg (election_id, election_type, candidate_count,election_date) VALUES (?,?,?,?)",
                    voteReg.getElection_id(), voteReg.getElection_type(), voteReg.getCandidate_count(), voteReg.getDate());
            if (isSaved) {
                boolean isAddCandidate = addCandidateDAO.save(addCandidates);
                if (isAddCandidate) {
                    con.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException er) {
            System.out.println(er);
            con.rollback();
            return false;
        } finally {

            con.setAutoCommit(true);
        }
    }

    @Override
    public boolean updateVote(VoteDTO voteDTO) throws SQLException, ClassNotFoundException {
        VoteReg vote = new VoteReg(voteDTO.getElection_id(),voteDTO.getElection_type(),voteDTO.getCandidate_count(),voteDTO.getDate());

        return voteRegDAO.update(vote);
    }

    @Override
    public String getCandidateName(Integer id) throws SQLException {
        return queryDAO.getCandidateName(id);
    }
}
