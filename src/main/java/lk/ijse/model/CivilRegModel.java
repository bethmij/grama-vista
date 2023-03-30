package lk.ijse.model;

import lk.ijse.db.DBConnection;
import lk.ijse.dto.Civil;
import lk.ijse.dto.MultiResidence;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class CivilRegModel {

    public static boolean save(Civil civil, List<MultiResidence> residenceList ) throws SQLException {

        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = CivilModel.save(civil);
            if(isSaved) {
                boolean isUpdated = ContactModel.save(civil);
                if(isUpdated) {
                    //boolean isOrdered = OrderDetailModel.save(oId, cartDTOList);
                    //if(isOrdered) {
                    con.commit();
                    return true;

                    }

            }
            return false;
        } catch (SQLException er) {
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }

    }

}
