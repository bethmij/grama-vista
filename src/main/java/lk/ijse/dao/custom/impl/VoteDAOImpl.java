package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.VoteDAO;

import lk.ijse.dao.custom.impl.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VoteDAOImpl implements VoteDAO {



    @Override
    public  List<String> getElecID(String civil_id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT election_id FROM grama_vista.vote WHERE civil_id=? ",civil_id);
        List<String> id = new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }
        return  id;
    }



    @Override
    public  boolean saveVote(String election_id, Integer candidate_id, Integer civilID) throws SQLException {


        return  CrudUtil.execute("INSERT INTO grama_vista.vote (election_id, civil_id, candidate_id, voted_date) VALUES (?,?,?,?)",
                election_id,civilID,candidate_id, LocalDate.now());

    }

    @Override
    public  Integer getCount() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(civil_id) FROM grama_vista.vote ");
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        return null;
    }

    @Override
    public  Integer getVotefor1(Integer id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(civil_id) FROM grama_vista.vote WHERE candidate_id=?",id);
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        return null;
    }
















}
