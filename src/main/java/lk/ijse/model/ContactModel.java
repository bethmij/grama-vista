package lk.ijse.model;

import lk.ijse.dto.Civil;
import lk.ijse.dto.Contact;
import lk.ijse.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ContactModel {

        public static boolean save(List<Contact> contactList ) throws SQLException {
            for(Contact list : contactList) {
                if(!save(list)) {
                    return false;
                }
            }
            return true;
        }

        private static boolean save( Contact contact ) throws SQLException {

            String sql = "INSERT INTO grama_vista.contact (reg_number, contact_num) " +
                    "VALUES (?,?)";

            return CrudUtil.execute(sql,contact.getCivil_id(),contact.getContact());
        }


}
