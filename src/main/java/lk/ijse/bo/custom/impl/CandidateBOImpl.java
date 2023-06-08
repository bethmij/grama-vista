package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CandidateBO;
import lk.ijse.dao.custom.CandidateDAO;
import lk.ijse.dao.custom.DetailDAO;
import lk.ijse.dao.custom.DivisionDAO;
import lk.ijse.dao.custom.impl.CandidateDAOImpl;
import lk.ijse.dao.custom.impl.DetailDAOImpl;
import lk.ijse.dao.custom.impl.DivisionDAOImpl;
import lk.ijse.dto.CandidateDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.entity.Candidate;
import lk.ijse.entity.Detail;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class CandidateBOImpl implements CandidateBO {

    DivisionDAO divisionDAO = new DivisionDAOImpl();
    CandidateDAO candidateDAO = new CandidateDAOImpl();
    DetailDAO detailDAO = new DetailDAOImpl();

    @Override
    public List<String> loadDivisionId() throws SQLException {
         return divisionDAO.loadDivisionID();
    }

    public boolean saveCandidate(CandidateDTO candidateDTO) throws SQLException, ClassNotFoundException {
        Candidate candidate = new Candidate(candidateDTO.getElection(), candidateDTO.getDivision(), candidateDTO.getNIC(), candidateDTO.getName(), candidateDTO.getPolitic(),
                candidateDTO.getAddress(), candidateDTO.getContact(), candidateDTO.getImage());
        return candidateDAO.save(candidate);
    }

    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }

    public boolean updateCandidate (CandidateDTO candidateDTO) throws SQLException, ClassNotFoundException {
        Candidate candidate = new Candidate(candidateDTO.getElection(), candidateDTO.getDivision(), candidateDTO.getNIC(), candidateDTO.getName(), candidateDTO.getPolitic(),
                candidateDTO.getAddress(), candidateDTO.getContact(), candidateDTO.getImage());
        return candidateDAO.update(candidate);
    }

    public boolean uploadCandidateImage(String text, InputStream in) throws SQLException {
        return candidateDAO.upload(text,in);
    }


}
