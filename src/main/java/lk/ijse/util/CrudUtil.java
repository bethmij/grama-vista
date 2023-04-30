package lk.ijse.util;

import lk.ijse.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {

    public static <T> T execute (String sql, Object...params) throws SQLException {

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        for(int i=0; i< params.length; i++){
            pstm.setObject((i+1),params[i]);
        }

        if(sql.startsWith("SELECT"))
            return (T) pstm.executeQuery();
        else
            return (T)(Boolean)(pstm.executeUpdate()>0);
    }
}
