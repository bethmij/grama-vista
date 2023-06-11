package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.LandDetailDAO;
import lk.ijse.entity.LandDetail;
import lk.ijse.dao.custom.impl.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class LandDetailDAOImpl implements LandDetailDAO {

    @Override
    public  boolean save(List<LandDetail> landDetailList) throws SQLException {
        for(LandDetail list : landDetailList) {
            if(!save(list)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean save(LandDetail landDetail) throws SQLException {

        String sql = "INSERT INTO grama_vista.land_detail ( type_id, land_num) VALUES (?,?)";

        return CrudUtil.execute(sql, landDetail.getType_id(), landDetail.getLand_num());
    }

    @Override
    public  boolean update(List<LandDetail> landDetailList) throws SQLException {
        for (LandDetail list : landDetailList) {
            if (!update(list)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean update(LandDetail landDetail) throws SQLException {

        return CrudUtil.execute("UPDATE grama_vista.land_detail SET land_num=? WHERE type_id=?",
                landDetail.getLand_num(), landDetail.getType_id());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature yet to be developed");
    }

    @Override
    public LandDetail search(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature yet to be developed");
    }

    @Override
    public List<LandDetail> searchAll() throws SQLException {
        throw new UnsupportedOperationException("This feature yet to be developed");
    }
}
