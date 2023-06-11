package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.ElecCandidateDTO;
import lk.ijse.dto.VoteDTO;

import java.sql.SQLException;
import java.util.List;

public interface VoteManageBO extends SuperBO {

    List<String> getElectID() throws SQLException;

    List<VoteDTO> searchAllVote() throws SQLException;

    VoteDTO searchVote(String id) throws SQLException, ClassNotFoundException;

    List<ElecCandidateDTO> searchCandidate(String id) throws SQLException;

    boolean deleteVote(String id) throws SQLException, ClassNotFoundException;

    void saveDetail(DetailDTO detail) throws SQLException;
}
