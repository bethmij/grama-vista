package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.VoteResultBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.*;
import lk.ijse.dao.custom.impl.*;
import lk.ijse.dto.DetailDTO;
import lk.ijse.entity.Detail;

import java.sql.SQLException;

public class VoteResultBOImpl implements VoteResultBO {

    CandidateDAO candidateDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CANDIDATEDAO);
    CivilDAO civilDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CIVILDAO);
    VoteDAO voteDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.VOTEDAO);
    QueryDAO queryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);
    DetailDAO detailDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DETAILDAO);

    @Override
    public Integer getCandidateCount() throws SQLException {
        return candidateDAO.getCount();
    }

    @Override
    public Integer getCivilCount() throws SQLException {
        return civilDAO.getCount();
    }

    @Override
    public Integer getVoteCount() throws SQLException {
        return voteDAO.getCount();
    }

    @Override
    public Integer getVote(Integer id) throws SQLException {
        return voteDAO.getVotefor1(id);
    }

    @Override
    public String getWinnerName(Integer id) throws SQLException {
        return queryDAO.getCandidateName(id);
    }

    @Override
    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }

}
