package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CandidateViewBO;
import lk.ijse.dao.custom.CandidateDAO;
import lk.ijse.dao.custom.impl.CandidateDAOImpl;

import java.sql.SQLException;



public class CandidateViewBOImpl implements CandidateViewBO {
    CandidateDAO candidateDAO = new CandidateDAOImpl();

    @Override
    public boolean deleteCandidate(String id) throws SQLException, ClassNotFoundException {
        return candidateDAO.delete(id);
    }
}
