package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

import java.sql.SQLException;

public interface CandidateViewBO extends SuperBO  {
    boolean deleteCandidate(String id) throws SQLException, ClassNotFoundException;
}
