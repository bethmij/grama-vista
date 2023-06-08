package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.CandidateDAO;

import lk.ijse.entity.Candidate;
import lk.ijse.dao.custom.impl.util.CrudUtil;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CandidateDAOImpl implements CandidateDAO {
    @Override
    public ArrayList<Candidate> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature yet to be developed");
    }

    @Override
    public  boolean save(Candidate candidate) throws SQLException {
        String sql = "INSERT INTO grama_vista.candidate ( elect_reg_num,division_id, nic, name,address, contact, political_party) " +
                "VALUES (?,?,?,?,?,?,?)";


        return  CrudUtil.execute(sql, candidate.getElection_id(), candidate.getDivision_id(), candidate.getNic(), candidate.getName(),
                candidate.getAddress(), candidate.getContact(), candidate.getPolitical_party());

    }

    @Override
    public  boolean upload(String id, InputStream in) throws SQLException {

        return CrudUtil.execute("UPDATE grama_vista.candidate SET image=? WHERE elect_reg_num=?",in,id);
    }

    @Override
    public  Candidate search(String election_id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.candidate WHERE elect_reg_num=?", election_id);
        if (resultSet.next()) {

            return new Candidate(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),resultSet.getString(4)
                    , resultSet.getString(7),resultSet.getString(5), resultSet.getInt(6),resultSet.getBlob(9));

        }
        return null;
    }

    @Override
    public  List<String> loadElectionID() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT elect_reg_num FROM grama_vista.candidate ");
        List<String> id = new ArrayList<>();

        while (resultSet.next()) {
            id.add(resultSet.getString(1));
        }

        return id;
    }

    @Override
    public  List<Candidate> searchAll() throws SQLException {
        List<Candidate> datalist = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.candidate ");
        while (resultSet.next()) {

            datalist.add((new Candidate(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),resultSet.getString(4)
                    , resultSet.getString(7),resultSet.getString(5), resultSet.getInt(6),resultSet.getBlob(9))));
            ;

        }
        return datalist;
    }

    @Override
    public  boolean update(Candidate candidate) throws SQLException {
        String sql = "UPDATE grama_vista.candidate SET division_id=?, nic=?, name=?,address=?, contact=?, political_party=? " +
                "WHERE elect_reg_num=?";


        return  CrudUtil.execute(sql, candidate.getDivision_id(), candidate.getNic(), candidate.getName(),
                candidate.getAddress(), candidate.getContact(), candidate.getPolitical_party(), candidate.getElection_id());

    }

    @Override
    public  String getName(String reg_id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT name FROM grama_vista.candidate WHERE elect_reg_num=?", reg_id);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return "";
    }

    @Override
    public  boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE  FROM grama_vista.candidate WHERE elect_reg_num=?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature yet to be developed");
    }

    @Override
    public  Integer getCount() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(elect_reg_num) FROM grama_vista.candidate");
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        return null;
    }
}
