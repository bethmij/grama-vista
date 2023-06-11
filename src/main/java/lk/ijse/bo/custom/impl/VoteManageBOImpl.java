package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.VoteManageBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.DetailDAO;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.dao.custom.VoteRegDAO;
import lk.ijse.dao.custom.impl.DetailDAOImpl;
import lk.ijse.dao.custom.impl.QueryDAOImpl;
import lk.ijse.dao.custom.impl.VoteRegDAOImpl;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.ElecCandidateDTO;
import lk.ijse.dto.VoteDTO;
import lk.ijse.entity.Detail;
import lk.ijse.entity.ElecCandidateEntity;
import lk.ijse.entity.VoteReg;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VoteManageBOImpl implements VoteManageBO {

    DetailDAO detailDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DETAILDAO);
    VoteRegDAO voteRegDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.VOTEREGDAO);
    QueryDAO queryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);

    @Override
    public List<String> getElectID() throws SQLException {
         return voteRegDAO.getElecID();
    }

    @Override
    public List<VoteDTO> searchAllVote() throws SQLException {
        List<VoteReg> voteRegs = voteRegDAO.searchAll();
        List<VoteDTO> voteDTO = new ArrayList<>();
        for (VoteReg voteReg : voteRegs) {
            voteDTO.add(new VoteDTO(voteReg.getElection_id(),voteReg.getElection_type(),voteReg.getCandidate_count(),voteReg.getDate()));
        }

        return voteDTO;
    }

    @Override
    public VoteDTO searchVote(String id) throws SQLException, ClassNotFoundException {
        VoteReg voteReg =  voteRegDAO.search(id);
        VoteDTO voteDTO = new VoteDTO(voteReg.getElection_id(),voteReg.getElection_type(),voteReg.getCandidate_count(),voteReg.getDate());
        return voteDTO;
    }

    @Override
    public List<ElecCandidateDTO> searchCandidate(String id) throws SQLException {
        List<ElecCandidateEntity> elecCandidate =  queryDAO.searchCandidate(id);
        List<ElecCandidateDTO> candidateDTOS = new ArrayList<>();
        for (ElecCandidateEntity elecCandidateEntity : elecCandidate) {
            candidateDTOS.add(new ElecCandidateDTO(elecCandidateEntity.getElection_id(),elecCandidateEntity.getElection_id(),elecCandidateEntity.getName(),elecCandidateEntity.getPolitic()));
        }
        return candidateDTOS;
    }

    @Override
    public boolean deleteVote(String id) throws SQLException, ClassNotFoundException {
        return voteRegDAO.delete(id);
    }

    @Override
    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }



}
