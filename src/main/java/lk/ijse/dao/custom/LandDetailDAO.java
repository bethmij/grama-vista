package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.LandDetail;

import java.sql.SQLException;
import java.util.List;

public interface LandDetailDAO extends CrudDAO<LandDetail, String> {

    boolean save(List<LandDetail> landDetailList) throws SQLException;

    boolean update(List<LandDetail> landDetailList) throws SQLException;

    boolean update(LandDetail landDetail) throws SQLException;
}
