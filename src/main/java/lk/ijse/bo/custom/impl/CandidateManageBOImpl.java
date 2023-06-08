package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CandidateManageBO;
import lk.ijse.dao.custom.CandidateDAO;
import lk.ijse.dao.custom.DetailDAO;
import lk.ijse.dao.custom.impl.CandidateDAOImpl;
import lk.ijse.dao.custom.impl.DetailDAOImpl;
import lk.ijse.dto.CandidateDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.entity.Candidate;
import lk.ijse.entity.Detail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CandidateManageBOImpl implements CandidateManageBO {
    CandidateDAO candidateDAO = new CandidateDAOImpl();
    DetailDAO detailDAO = new DetailDAOImpl();

    @Override
    public List<String> loadElectionId() throws SQLException {
        return candidateDAO.loadElectionID();
    }

    public List<CandidateDTO> searchAllCandidate() throws SQLException {
        List<Candidate> candidate = candidateDAO.searchAll();
        List<CandidateDTO> candidateDTO = new ArrayList<>();
        for (Candidate candidate1 : candidate) {
            candidateDTO.add(new CandidateDTO(candidate1.getElection_id(), candidate1.getImage(), candidate1.getName(), candidate1.getNic(),candidate1.getDivision_id(),
                    candidate1.getAddress(), candidate1.getContact(), candidate1.getPolitical_party()));

        }
        return candidateDTO;
    }

    public CandidateDTO searchCandidate(String id) throws SQLException, ClassNotFoundException {
        Candidate candidate = candidateDAO.search(id);
        CandidateDTO candidateDTO = (new CandidateDTO(candidate.getElection_id(), candidate.getImage(), candidate.getName(), candidate.getNic(),candidate.getDivision_id(),
                candidate.getAddress(), candidate.getContact(), candidate.getPolitical_party()));
        return candidateDTO;
    }

    public String getCandidateName(String id) throws SQLException {
        return candidateDAO.getName(id);
    }

    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }
}
