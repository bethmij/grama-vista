package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.VoteDTO;
import lk.ijse.entity.AddCandidate;
import lk.ijse.entity.Vote;
import lk.ijse.entity.VoteReg;

import java.sql.SQLException;
import java.util.List;

public interface VoteRegDAO extends CrudDAO<VoteReg,String> {

    List<String> getElecID() throws SQLException;

    boolean updateCount(String election_id) throws SQLException;
}
