package lk.ijse.bo.custom;

import java.io.InputStream;
import java.sql.SQLException;

public interface CameraBO {
    Integer getCivilId(String nic) throws SQLException;

    boolean uploadImage(String id, InputStream in) throws SQLException;
}
