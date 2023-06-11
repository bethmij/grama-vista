package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

import java.sql.SQLException;
import java.util.Map;

public interface DashboardBO extends SuperBO {
    Map<Integer, Integer> getDateDiff() throws SQLException;

    boolean sentMail(Integer key) throws SQLException;

    String getEmail(Integer id) throws SQLException;

    String getCivilName(String id) throws SQLException;

    void updateEmail(Integer id, String to) throws SQLException;
}
