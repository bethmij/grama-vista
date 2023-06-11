package lk.ijse.dao.custom;


import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Civil;
import lk.ijse.entity.Contact;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CivilDAO extends CrudDAO<Civil,String> {

    String generateNewID() throws SQLException;

    boolean upload(String id, InputStream in) throws SQLException;

    List<String> loadCivilId() throws SQLException;

    String searchById(String id) throws SQLException;

    String getName(String reg_id) throws SQLException;

    Map<Integer, Integer> getDateDiff() throws SQLException;

    String getEmail(Integer id) throws SQLException;

    Integer getMale() throws SQLException;

    Integer getFemale() throws SQLException;

    List<String> loadElectCivilId() throws SQLException;

    Integer getCount() throws SQLException;

    boolean updateEmail(Integer id, String to) throws SQLException;

    boolean isMailSent(Object id) throws SQLException;

    Civil searchbyNIC(String nic) throws SQLException;

    Integer getID(String nic) throws SQLException;
}
