package lk.ijse.dao.custom;



import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SuperDAO;
import lk.ijse.dao.custom.impl.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface VoteDAO extends SuperDAO {
    //Didn't extend crudDAO as this didn't use other crud operations n might be not needed

    List<String> getElecID(String civil_id) throws SQLException;

    boolean saveVote(String election_id, Integer candidate_id, Integer civilID) throws SQLException;

    Integer getCount() throws SQLException;

    Integer getVotefor1(Integer id) throws SQLException;





}
