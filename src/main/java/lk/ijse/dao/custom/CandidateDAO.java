package lk.ijse.dao.custom;


import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Candidate;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CandidateDAO extends CrudDAO<Candidate, String> {


    ArrayList<Candidate> getAll() throws SQLException, ClassNotFoundException;

    boolean upload(String id, InputStream in) throws SQLException;



    List<String> loadElectionID() throws SQLException;





    String getName(String reg_id) throws SQLException;


    String generateNewID() throws SQLException, ClassNotFoundException;

    Integer getCount() throws SQLException;
}
