package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.ContactDAO;
import lk.ijse.entity.Contact;
import lk.ijse.dao.custom.impl.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDAOImpl implements ContactDAO {

        @Override
        public  boolean save(List<Contact> contactList) throws SQLException {
            for(Contact list : contactList) {
                if(!save(list)) {
                    return false;
                }
            }
            return true;
        }
    @Override
    public boolean save(Contact contact) throws SQLException {

        String sql = "INSERT INTO grama_vista.contact (reg_number, contact_num) " +
                "VALUES (?,?)";

        return CrudUtil.execute(sql, contact.getCivil_id(), contact.getContact());
    }


    @Override
    public  boolean update(List<Contact> contactList) throws SQLException {

        for (Contact list : contactList) {
            if (!update(list)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean update(Contact contact) throws SQLException {

        return CrudUtil.execute("UPDATE grama_vista.contact SET  contact_num=? WHERE reg_number=?", contact.getContact(), contact.getCivil_id());
    }

    @Override
    public  List<Contact>  searchContact(String reg_ig) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM grama_vista.contact WHERE reg_number=?",reg_ig);
        List<Contact> contactList = new ArrayList<>();
        while (resultSet.next()){
            contactList.add(new Contact(resultSet.getString(1),resultSet.getInt(2)));
        }
        return contactList;
    }

}
