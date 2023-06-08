package lk.ijse.bo.custom;

import java.sql.SQLException;

public interface CandidateViewBO {
    boolean deleteCandidate(String id) throws SQLException, ClassNotFoundException;
}
