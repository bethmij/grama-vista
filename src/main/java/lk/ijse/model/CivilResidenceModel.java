package lk.ijse.model;

import lk.ijse.db.DBConnection;
import lk.ijse.dto.Civil;
import lk.ijse.dto.Contact;
import lk.ijse.dto.MultiResidence;
import lk.ijse.util.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CivilResidenceModel {

    public static boolean save(Civil civil , List<Contact> contact, List<MultiResidence> multiResidence, String division_id) throws SQLException {

        Connection con = null;
        try {
            System.out.println(1);
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            /*boolean isCivilSaved = CivilModel.save(civil);
            System.out.println("ghgfdd");
            if(isCivilSaved) {

               // boolean isContactSaved = ContactModel.save(contact);
                ContactModel.save(contact);
                System.out.println("sd");
                MultiResidenceModel.save(multiResidence);
                System.out.println("hgf");
                con.commit();
                return true;
                */

            boolean isCivilSaved = CivilModel.save(civil);
            if (isCivilSaved) {
                boolean isContactSaved = ContactModel.save(contact);
                if (isContactSaved) {
                    boolean isResidenceSaved = MultiResidenceModel.save(multiResidence);
                    if (isResidenceSaved) {
                        boolean isPopulationUpdate = DivisionModel.UpdatePopulation(division_id);
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

    public static boolean update(Civil civil, List<Contact> contactList, List<MultiResidence> residenceList) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isCivilSaved = CivilModel.update(civil);
            if (isCivilSaved) {
                boolean isContactSaved = ContactModel.update(contactList);
                if (isContactSaved) {
                    boolean isResidenceSaved = MultiResidenceModel.save(residenceList);
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
