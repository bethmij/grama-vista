package lk.ijse.dao.custom;



import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.custom.impl.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface VoteDAO {


    List<String> getElecID(String civil_id) throws SQLException;



    boolean saveVote(String election_id, Integer candidate_id, Integer civilID) throws SQLException;

    Integer getCount() throws SQLException;

    Integer getVotefor1(Integer id) throws SQLException;





}
