package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.IndividualFormBO;
import lk.ijse.dao.custom.CivilDAO;
import lk.ijse.dao.custom.CivilResidenceDAO;
import lk.ijse.dao.custom.DetailDAO;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.dao.custom.impl.CivilDAOImpl;
import lk.ijse.dao.custom.impl.CivilResidenceDAOImpl;
import lk.ijse.dao.custom.impl.DetailDAOImpl;
import lk.ijse.dao.custom.impl.QueryDAOImpl;
import lk.ijse.dto.*;
import lk.ijse.entity.*;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IndividualFormBOImpl implements IndividualFormBO {
    DetailDAO detailDAO = new DetailDAOImpl();
    QueryDAO queryDAO = new QueryDAOImpl();
    CivilResidenceDAO civilResidenceDAO = new CivilResidenceDAOImpl();
    CivilDAO civilDAO = new CivilDAOImpl();

    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }

    public String getDivisionId(Integer id) throws SQLException {
        return queryDAO.getDivisionId(id);
    }

    public boolean saveCivil(CivilDTO civil, List<ContactDTO>contactList, List<MultiResidenceDTO>residenceList, String division_id) throws SQLException {
        Civil civil1 = new Civil(civil.getID(), civil.getImage(), civil.getName(), civil.getNic(), civil.getAddress(), civil.getDob()
                , civil.getAge(), civil.getGender(), civil.getMarriage(), civil.getRelation(), civil.getEducation(), civil.getSchool(), civil.getOccupation()
                , civil.getWork(), civil.getSalary(), civil.getEmail());

        List<Contact> contacts = new ArrayList<>();
        for (ContactDTO contactDTO : contactList) {
            contacts.add(new Contact(contactDTO.getCivil_id(),contactDTO.getContact()));
        }

        List<MultiResidence> residences = new ArrayList<>();
        for (MultiResidenceDTO  residenceDTO : residenceList) {
            residences.add(new MultiResidence(residenceDTO.getResidence_id(),residenceDTO.getCivil_id()));
        }

       return civilResidenceDAO.save(civil1, contacts, residences, division_id);
    }

    public boolean updateCivil(CivilDTO civil, List<ContactDTO>contactList, List<MultiResidenceDTO>residenceList) throws SQLException {
        Civil civil1 = new Civil(civil.getID(), civil.getImage(), civil.getName(), civil.getNic(), civil.getAddress(), civil.getDob()
                , civil.getAge(), civil.getGender(), civil.getMarriage(), civil.getRelation(), civil.getEducation(), civil.getSchool(), civil.getOccupation()
                , civil.getWork(), civil.getSalary(), civil.getEmail());

        List<Contact> contacts = new ArrayList<>();
        for (ContactDTO contactDTO : contactList) {
            contacts.add(new Contact(contactDTO.getCivil_id(),contactDTO.getContact()));
        }

        List<MultiResidence> residences = new ArrayList<>();
        for (MultiResidenceDTO  residenceDTO : residenceList) {
            residences.add(new MultiResidence(residenceDTO.getResidence_id(),residenceDTO.getCivil_id()));
        }

        return civilResidenceDAO.update(civil1, contacts, residences);


    }

    public Integer getCivilId(String nic ) throws SQLException {
        return civilDAO.getID(nic);
    }

    public boolean uploadImage(String id, InputStream in) throws SQLException {
        return civilDAO.upload(id, in);
    }
}
