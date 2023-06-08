package lk.ijse.dao.custom;

import lk.ijse.entity.Land;
import lk.ijse.entity.LandDetail;
import lk.ijse.entity.CoOwner;

import java.sql.SQLException;
import java.util.List;

public interface LandDAO {
    Integer getNextLandId() throws SQLException;



    Integer getLandNum(String plan_num) throws SQLException;

    List<String> loadLandID() throws SQLException;

    Land search(String id) throws SQLException;

    List<Land> searchAll() throws SQLException;



    Integer getCount() throws SQLException;

    boolean delete(String id) throws SQLException;

    public boolean saveLand (Land land) throws SQLException;

    public boolean updateLand (Land land) throws SQLException;
}
