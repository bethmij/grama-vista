package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.VoteRegDAO;
import lk.ijse.db.DBConnection;

import lk.ijse.entity.AddCandidate;
import lk.ijse.entity.VoteReg;
import lk.ijse.dao.custom.impl.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VoteRegDAOImpl implements VoteRegDAO {


    @Override
    public  List<String> getElecID() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT election_id FROM grama_vista.votereg ");
        List<String> id = new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }
        return  id;
    }

    @Override
    public VoteReg search(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.votereg WHERE election_id=?",id);
        if(resultSet.next()){
            return new VoteReg(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getDate(4).toLocalDate());
        }
        return null;
    }

    @Override
    public  boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE  FROM grama_vista.votereg WHERE election_id=?", id);
    }


    @Override
    public  List<VoteReg> searchAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.votereg ");
        List<VoteReg> voteRegList = new ArrayList<>();
        while (resultSet.next()){
            voteRegList.add(new VoteReg(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getDate(4).toLocalDate()));
        }
        return voteRegList;
    }


    @Override
    public boolean save(VoteReg dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public  boolean update(VoteReg voteReg) throws SQLException {
        return CrudUtil.execute("UPDATE grama_vista.votereg SET election_type=?, candidate_count=?, election_date=? WHERE election_id=?",
                voteReg.getElection_type(), voteReg.getCandidate_count(), voteReg.getDate(), voteReg.getElection_id());
    }


    @Override
    public  boolean updateCount(String election_id) throws SQLException {
        return CrudUtil.execute("UPDATE grama_vista.votereg SET candidate_count=candidate_count+1 WHERE election_id=?",election_id);
    }
}
