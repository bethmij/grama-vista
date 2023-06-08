package lk.ijse.bo.custom;

import lk.ijse.dto.AddCandidateDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.VoteDTO;

import java.sql.SQLException;
import java.util.List;

public interface VoteBO {

    List<String> loadElectionID() throws SQLException;

    void saveDetail(DetailDTO detail) throws SQLException;

    boolean saveVote(VoteDTO voteDTO, List<AddCandidateDTO> addCandidateList) throws SQLException;

    boolean updateVote(VoteDTO voteDTO) throws SQLException;

    String getCandidateName(Integer id) throws SQLException;
}
