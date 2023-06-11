package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.VoteLoginBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CivilDAO;
import lk.ijse.dao.custom.DetailDAO;
import lk.ijse.dao.custom.VoteDAO;
import lk.ijse.dao.custom.VoteRegDAO;
import lk.ijse.dao.custom.impl.CivilDAOImpl;
import lk.ijse.dao.custom.impl.DetailDAOImpl;
import lk.ijse.dao.custom.impl.VoteDAOImpl;
import lk.ijse.dao.custom.impl.VoteRegDAOImpl;
import lk.ijse.dto.CivilDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.VoteDTO;
import lk.ijse.entity.Civil;
import lk.ijse.entity.Detail;
import lk.ijse.entity.VoteReg;

import java.sql.SQLException;
import java.util.List;

public class VoteLoginBOImpl implements VoteLoginBO {

    VoteDAO voteDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.VOTEDAO);
    VoteRegDAO voteRegDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.VOTEREGDAO);
    CivilDAO civilDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CIVILDAO);
    DetailDAO detailDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DETAILDAO);

    @Override
    public VoteDTO searchVote(String id) throws SQLException, ClassNotFoundException {
         VoteReg voteReg =  voteRegDAO.search(id);
         VoteDTO voteDTO = new VoteDTO(voteReg.getElection_id(),voteReg.getElection_type(),voteReg.getCandidate_count(),voteReg.getDate());
         return voteDTO;
    }

    @Override
    public  CivilDTO searchByCivilNIC(String nic) throws SQLException {
        Civil civil = civilDAO.searchbyNIC(nic);
        return new CivilDTO(civil.getID(), civil.getImage(), civil.getName(), civil.getNic(), civil.getAddress(), civil.getDob()
                , civil.getAge(), civil.getGender(), civil.getMarriage(), civil.getRelation(), civil.getEducation(), civil.getSchool(), civil.getOccupation()
                , civil.getWork(), civil.getSalary(), civil.getEmail());
    }

    @Override
    public List<String> getElectionIDbyID(String id) throws SQLException {
        return voteDAO.getElecID(id);
    }

    @Override
    public List<String> getElectionID() throws SQLException {
        return voteRegDAO.getElecID();
    }

    @Override
    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }
}
