package lk.ijse.bo.custom;

import lk.ijse.dto.CandidateDTO;
import lk.ijse.dto.DetailDTO;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public interface CandidateBO {
    List<String> loadDivisionId() throws SQLException;

    boolean saveCandidate(CandidateDTO candidateDTO) throws SQLException, ClassNotFoundException;

    void saveDetail(DetailDTO detail) throws SQLException;

    boolean updateCandidate (CandidateDTO candidateDTO) throws SQLException, ClassNotFoundException;

    boolean uploadCandidateImage(String text, InputStream in) throws SQLException;
}
