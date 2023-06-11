package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.custom.impl.util.CrudUtil;
import lk.ijse.entity.Contact;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ContactDAO extends CrudDAO<Contact , String> {

    boolean save(List<Contact> contactList) throws SQLException;

    boolean update(List<Contact> contactList) throws SQLException;

    List<Contact>  searchContact(String reg_ig) throws SQLException;

}
