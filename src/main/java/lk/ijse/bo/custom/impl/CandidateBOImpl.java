package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CandidateBO;
import lk.ijse.dao.DAOFactory;
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

    DivisionDAO divisionDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DIVISIONDAO);
    CandidateDAO candidateDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CANDIDATEDAO);
    DetailDAO detailDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DETAILDAO);

    @Override
    public List<String> loadDivisionId() throws SQLException {
         return divisionDAO.loadDivisionID();
    }

    @Override
    public boolean saveCandidate(CandidateDTO candidateDTO) throws SQLException, ClassNotFoundException {
        Candidate candidate = new Candidate(candidateDTO.getElection(), candidateDTO.getDivision(), candidateDTO.getNIC(), candidateDTO.getName(), candidateDTO.getPolitic(),
                candidateDTO.getAddress(), candidateDTO.getContact(), candidateDTO.getImage());
        return candidateDAO.save(candidate);
    }

    @Override
    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }

    @Override
    public boolean updateCandidate (CandidateDTO candidateDTO) throws SQLException, ClassNotFoundException {
        Candidate candidate = new Candidate(candidateDTO.getElection(), candidateDTO.getDivision(), candidateDTO.getNIC(), candidateDTO.getName(), candidateDTO.getPolitic(),
                candidateDTO.getAddress(), candidateDTO.getContact(), candidateDTO.getImage());
        return candidateDAO.update(candidate);
    }

    @Override
    public boolean uploadCandidateImage(String text, InputStream in) throws SQLException {
        return candidateDAO.upload(text,in);
    }


}
