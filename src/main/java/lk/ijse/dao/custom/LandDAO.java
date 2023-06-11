package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Land;
import lk.ijse.entity.LandDetail;
import lk.ijse.entity.CoOwner;

import java.sql.SQLException;
import java.util.List;

public interface LandDAO extends CrudDAO<Land , String> {

    Integer getNextLandId() throws SQLException;

    Integer getLandNum(String plan_num) throws SQLException;

    List<String> loadLandID() throws SQLException;

    Integer getCount() throws SQLException;

}
