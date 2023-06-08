package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.VoteeBO;
import lk.ijse.dao.custom.DetailDAO;
import lk.ijse.dao.custom.VoteDAO;
import lk.ijse.dao.custom.impl.DetailDAOImpl;
import lk.ijse.dao.custom.impl.VoteDAOImpl;
import lk.ijse.dto.DetailDTO;
import lk.ijse.entity.Detail;

import java.sql.SQLException;

public class VoteeBOImpl implements VoteeBO {
    DetailDAO detailDAO = new DetailDAOImpl();
    VoteDAO voteDAO = new VoteDAOImpl();


    @Override
    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }

    public boolean saveVote(String election_id, Integer candidate_id , Integer civilID) throws SQLException {
        return voteDAO.saveVote(election_id, candidate_id ,civilID);

    }


}
