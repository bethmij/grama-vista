package lk.ijse.dao.custom;

import lk.ijse.entity.Civil;
import lk.ijse.entity.Contact;
import lk.ijse.entity.MultiResidence;

import java.sql.SQLException;
import java.util.List;

public interface CivilResidenceDAO {
    boolean save(Civil civil, List<Contact> contact, List<MultiResidence> multiResidence, String division_id) throws SQLException;

    boolean update(Civil civil, List<Contact> contactList, List<MultiResidence> residenceList) throws SQLException;
}
