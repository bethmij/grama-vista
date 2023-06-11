package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.IndividualFormBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.*;
import lk.ijse.dao.custom.impl.*;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.*;
import lk.ijse.entity.*;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lk.ijse.controller.IndividualFormController.civil1DTO;

public class IndividualFormBOImpl implements IndividualFormBO {

    DetailDAO detailDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DETAILDAO);
    QueryDAO queryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);
    CivilDAO civilDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CIVILDAO);
    ContactDAO contactDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CONTACTDAO);
    MultiResidenceDAO multiResidenceDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.MULTIRESIDENCEDAO);
    DivisionDAO divisionDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DIVISIONDAO);

    @Override
    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }

    @Override
    public String getDivisionId(Integer id) throws SQLException {
        return queryDAO.getDivisionId(id);
    }

    @Override
    public boolean saveCivil(CivilDTO civil) throws SQLException {
        Civil civil1 = new Civil(civil.getID(), civil.getImage(), civil.getName(), civil.getNic(), civil.getAddress(), civil.getDob()
                , civil.getAge(), civil.getGender(), civil.getMarriage(), civil.getRelation(), civil.getEducation(), civil.getSchool(), civil.getOccupation()
                , civil.getWork(), civil.getSalary(), civil.getEmail());

        List<Contact> contacts = new ArrayList<>();
        for (ContactDTO contactDTO : civil.getContactLists()) {
            contacts.add(new Contact(contactDTO.getCivil_id(),contactDTO.getContact()));
        }

        List<MultiResidence> residences = new ArrayList<>();
        for (MultiResidenceDTO  residenceDTO : civil.getResidenceLists()) {
            residences.add(new MultiResidence(residenceDTO.getResidence_id(),residenceDTO.getCivil_id()));
        }

        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isCivilSaved = civilDAO.save(civil1);
            Integer id = civilDAO.getID(civil1.getNic());
            if (isCivilSaved) {
                for (int i=0; i< contacts.size(); i++){
                    contacts.get(i).setCivil_id(String.valueOf(id));
                }
                boolean isContactSaved = contactDAO.save(contacts);
                if (isContactSaved) {
                    for (int i=0; i< residences.size(); i++){
                        residences.get(i).setCivil_id(String.valueOf(id));
                    }
                    boolean isResidenceSaved = multiResidenceDAO.save(residences);
                    if (isResidenceSaved) {
                        String division_id = getDivisionId(id);
                        boolean isPopulationUpdate = divisionDAO.UpdatePopulation(division_id);
                        if(isPopulationUpdate) {
                            con.commit();
                            return true;
                        }

                    }
                }
            }

            return false;
        } catch (SQLException | ClassNotFoundException er) {
            System.out.println(er);
            con.rollback();
            return false;
        } finally {
            con.setAutoCommit(true);
        }
    }

    @Override
    public boolean updateCivil(CivilDTO civil) throws SQLException {
        Civil civil1 = new Civil(civil.getID(), civil.getImage(), civil.getName(), civil.getNic(), civil.getAddress(), civil.getDob()
                , civil.getAge(), civil.getGender(), civil.getMarriage(), civil.getRelation(), civil.getEducation(), civil.getSchool(), civil.getOccupation()
                , civil.getWork(), civil.getSalary(), civil.getEmail());

        List<Contact> contacts = new ArrayList<>();
        for (ContactDTO contactDTO : civil.getContactLists()) {
            contacts.add(new Contact(contactDTO.getCivil_id(),contactDTO.getContact()));
        }

        List<MultiResidence> residences = new ArrayList<>();
        for (MultiResidenceDTO  residenceDTO : civil.getResidenceLists()) {
            residences.add(new MultiResidence(residenceDTO.getResidence_id(),residenceDTO.getCivil_id()));
        }

        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isCivilSaved = civilDAO.update(civil1);
            Integer id = civilDAO.getID(civil1.getNic());
            if (isCivilSaved) {
                for (int i=0; i< contacts.size(); i++){
                    contacts.get(i).setCivil_id(String.valueOf(id));
                }
                boolean isContactSaved = contactDAO.update(contacts);
                if (isContactSaved) {
                    for (int i=0; i< residences.size(); i++){
                        residences.get(i).setCivil_id(String.valueOf(id));
                    }
                    boolean isResidenceSaved = multiResidenceDAO.save(residences);
                    if (isResidenceSaved) {
                        con.commit();
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException | ClassNotFoundException er) {
            con.rollback();
            return false;
        } finally {
            con.setAutoCommit(true);
        }


    }

    @Override
    public Integer getCivilId(String nic ) throws SQLException {
        return civilDAO.getID(nic);
    }

    @Override
    public boolean uploadImage(String id, InputStream in) throws SQLException {
        return civilDAO.upload(id, in);
    }
}
