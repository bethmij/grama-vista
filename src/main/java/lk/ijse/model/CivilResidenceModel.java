package lk.ijse.model;

import lk.ijse.db.DBConnection;
import lk.ijse.dto.Civil;
import lk.ijse.dto.Contact;
import lk.ijse.dto.MultiResidence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CivilResidenceModel {

    public static boolean save(Civil civil , List<Contact> contact, List<MultiResidence> multiResidence) throws SQLException {

        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isCivilSaved = CivilModel.save(civil);
            System.out.println(1);
            if(isCivilSaved) {

                boolean isContactSaved = ContactModel.save(contact);
                if(isContactSaved) {
                    boolean isResidenceSaved = MultiResidenceModel.save(multiResidence);
                    if(isResidenceSaved) {
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
