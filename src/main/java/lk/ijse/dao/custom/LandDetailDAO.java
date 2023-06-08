package lk.ijse.dao.custom;

import lk.ijse.entity.LandDetail;

import java.sql.SQLException;
import java.util.List;

public interface LandDetailDAO {
    boolean saveDetail(List<LandDetail> landDetailList) throws SQLException;

    boolean saveDetail(LandDetail landDetail) throws SQLException;

    boolean updateDetail(List<LandDetail> landDetailList) throws SQLException;

    boolean updateDetail(LandDetail landDetail) throws SQLException;
}
