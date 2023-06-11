package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CandidateDTO;
import lk.ijse.dto.DetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface CandidateManageBO extends SuperBO {
    List<String> loadElectionId() throws SQLException;

    List<CandidateDTO> searchAllCandidate() throws SQLException;

    CandidateDTO searchCandidate(String id) throws SQLException, ClassNotFoundException;

    String getCandidateName(String id) throws SQLException;

    void saveDetail(DetailDTO detail) throws SQLException;
}
