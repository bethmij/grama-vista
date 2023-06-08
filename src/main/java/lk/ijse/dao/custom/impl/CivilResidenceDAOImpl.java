package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.CivilResidenceDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.entity.Civil;
import lk.ijse.entity.Contact;
import lk.ijse.entity.MultiResidence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CivilResidenceDAOImpl implements CivilResidenceDAO {

    @Override
    public  boolean save(Civil civil, List<Contact> contact, List<MultiResidence> multiResidence, String division_id) throws SQLException {

        Connection con = null;
        try {

            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isCivilSaved = CivilDAOImpl.save(civil);
            Integer id = CivilDAOImpl.getID(civil.getNic());
            if (isCivilSaved) {
                for (int i=0; i< contact.size(); i++){
                    contact.get(i).setCivil_id(String.valueOf(id));
                }
                boolean isContactSaved = ContactDAOImpl.save(contact);
                if (isContactSaved) {
                    for (int i=0; i< multiResidence.size(); i++){
                        multiResidence.get(i).setCivil_id(String.valueOf(id));
                    }
                    boolean isResidenceSaved = MultiResidenceDAOImpl.save(multiResidence);
                    if (isResidenceSaved) {
                        boolean isPopulationUpdate = DivisionDAOImpl.UpdatePopulation(division_id);
                        if(isPopulationUpdate) {
                            con.commit();
                            return true;
                        }

                    }
                }
            }

            return false;
        } catch (SQLException er) {
            System.out.println(er);
            con.rollback();
            return false;
        } finally {
            con.setAutoCommit(true);
        }

    }

    @Override
    public  boolean update(Civil civil, List<Contact> contactList, List<MultiResidence> residenceList) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isCivilSaved = CivilDAOImpl.update(civil);
            Integer id = CivilDAOImpl.getID(civil.getNic());
            if (isCivilSaved) {
                for (int i=0; i< contactList.size(); i++){
                    contactList.get(i).setCivil_id(String.valueOf(id));
                }
                boolean isContactSaved = ContactDAOImpl.update(contactList);
                if (isContactSaved) {
                    for (int i=0; i< residenceList.size(); i++){
                        residenceList.get(i).setCivil_id(String.valueOf(id));
                    }
                    boolean isResidenceSaved = MultiResidenceDAOImpl.save(residenceList);
                    if (isResidenceSaved) {
                        con.commit();
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException er) {
            con.rollback();
            return false;
        } finally {
            con.setAutoCommit(true);
        }


    }
}
