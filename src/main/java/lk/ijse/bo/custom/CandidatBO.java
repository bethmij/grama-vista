package lk.ijse.bo.custom;

import lk.ijse.dto.AddCandidateDTO;

import java.sql.SQLException;
import java.util.List;

public interface CandidatBO {
    boolean deleteVote(String id) throws SQLException, ClassNotFoundException;

    List<String> loadElectionId() throws SQLException;

    String getCandidateName(Integer id) throws SQLException;

    boolean saveCandidate(List<AddCandidateDTO> addCandidateList) throws SQLException;

    boolean updateVote(String id) throws SQLException;
}
