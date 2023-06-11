package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CivilManageBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.*;
import lk.ijse.dao.custom.impl.*;
import lk.ijse.dto.CivilDTO;
import lk.ijse.dto.ContactDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.MultiResidenceDTO;
import lk.ijse.entity.Civil;
import lk.ijse.entity.Contact;
import lk.ijse.entity.Detail;
import lk.ijse.entity.MultiResidence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CivilManageBOImpl implements CivilManageBO {

    CivilDAO civilDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CIVILDAO);
    ContactDAO contactDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CONTACTDAO);
    MultiResidenceDAO multiResidenceDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.MULTIRESIDENCEDAO);
    DetailDAO detailDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DETAILDAO);

    @Override
    public List<String> loadCivilId() throws SQLException {
        return civilDAO.loadCivilId();
    }

    @Override
    public  List<CivilDTO>  searchAllCivil () throws SQLException {
        List<Civil> civilList  = civilDAO.searchAll();
        List<CivilDTO> civilDTOS = new ArrayList<>();
        for (Civil civil : civilList) {
            civilDTOS.add(new CivilDTO(civil.getID(), civil.getImage(), civil.getName(), civil.getNic(), civil.getAddress(), civil.getDob()
                    , civil.getAge(), civil.getGender(), civil.getMarriage(), civil.getRelation(), civil.getEducation(), civil.getSchool(), civil.getOccupation()
                    , civil.getWork(), civil.getSalary(), civil.getEmail()));
        }
        return civilDTOS;
    }

    @Override
    public CivilDTO searchCivil(String id) throws SQLException, ClassNotFoundException {
        Civil civil = civilDAO.search(id);

        return new CivilDTO(civil.getID(), civil.getImage(), civil.getName(), civil.getNic(), civil.getAddress(), civil.getDob()
                , civil.getAge(), civil.getGender(), civil.getMarriage(), civil.getRelation(), civil.getEducation(), civil.getSchool(), civil.getOccupation()
                , civil.getWork(), civil.getSalary(), civil.getEmail());
    }

    @Override
    public List<ContactDTO> searchContact(String id) throws SQLException {
        List<Contact> contactList = contactDAO.searchContact(id);
        List<ContactDTO> contactDTOS = new ArrayList<>();
        for (Contact contactDTO : contactList) {
            contactDTOS.add(new ContactDTO(contactDTO.getCivil_id(),contactDTO.getContact()));
        }
        return contactDTOS;
    }

    @Override
    public List<MultiResidenceDTO> searchResidence(String id) throws SQLException {
        List<MultiResidence> multiResidences = multiResidenceDAO.searchResidence(id);
        List<MultiResidenceDTO> multiResidenceDTOS = new ArrayList<>();
        for (MultiResidence multiResidence : multiResidences) {
            multiResidenceDTOS.add(new MultiResidenceDTO(multiResidence.getResidence_id(),multiResidence.getCivil_id()));
        }
        return multiResidenceDTOS;
    }

    @Override
    public String getCivilName(String id) throws SQLException {
       return   civilDAO.getName(id);
    }

    @Override
    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }
}
