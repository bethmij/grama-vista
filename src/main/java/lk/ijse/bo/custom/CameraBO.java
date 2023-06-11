package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

import java.io.InputStream;
import java.sql.SQLException;

public interface CameraBO extends SuperBO {
    Integer getCivilId(String nic) throws SQLException;

    boolean uploadImage(String id, InputStream in) throws SQLException;
}
