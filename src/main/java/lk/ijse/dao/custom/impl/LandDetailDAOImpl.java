package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.LandDetailDAO;
import lk.ijse.entity.LandDetail;
import lk.ijse.dao.custom.impl.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class LandDetailDAOImpl implements LandDetailDAO {

    @Override
    public  boolean saveDetail(List<LandDetail> landDetailList) throws SQLException {
        for(LandDetail list : landDetailList) {
            if(!saveDetail(list)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean saveDetail(LandDetail landDetail) throws SQLException {

        String sql = "INSERT INTO grama_vista.land_detail ( type_id, land_num) VALUES (?,?)";

        return CrudUtil.execute(sql, landDetail.getType_id(), landDetail.getLand_num());
    }

    @Override
    public  boolean updateDetail(List<LandDetail> landDetailList) throws SQLException {
        for (LandDetail list : landDetailList) {
            if (!updateDetail(list)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateDetail(LandDetail landDetail) throws SQLException {

        return CrudUtil.execute("UPDATE grama_vista.land_detail SET land_num=? WHERE type_id=?",
                landDetail.getLand_num(), landDetail.getType_id());
    }
}
