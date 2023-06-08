package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.VoteResultBO;
import lk.ijse.dao.custom.*;
import lk.ijse.dao.custom.impl.*;
import lk.ijse.dto.DetailDTO;
import lk.ijse.entity.Detail;

import java.sql.SQLException;

public class VoteResultBOImpl implements VoteResultBO {
    CandidateDAO candidateDAO = new CandidateDAOImpl();
    CivilDAO civilDAO = new CivilDAOImpl();
    VoteDAO voteDAO = new VoteDAOImpl();
    QueryDAO queryDAO = new QueryDAOImpl();
    DetailDAO detailDAO = new DetailDAOImpl();

    public Integer getCandidateCount() throws SQLException {
        return candidateDAO.getCount();
    }

    public Integer getCivilCount() throws SQLException {
        return civilDAO.getCount();
    }

    public Integer getVoteCount() throws SQLException {
        return voteDAO.getCount();
    }

    public Integer getVote(Integer id) throws SQLException {
        return voteDAO.getVotefor1(id);
    }

    public String getWinnerName(Integer id) throws SQLException {
        return queryDAO.getCandidateName(id);
    }

    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }

}
