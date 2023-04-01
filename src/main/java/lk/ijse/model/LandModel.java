package lk.ijse.model;

import lk.ijse.db.DBConnection;
import lk.ijse.dto.Land;
import lk.ijse.dto.LandDetail;
import lk.ijse.dto.Owner;
import lk.ijse.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LandModel {
    public static Integer getNextLandId() throws SQLException {

        ResultSet resultSet = CrudUtil.execute("SELECT land_num FROM grama_vista.land ORDER BY land_num DESC LIMIT 1" );

        if (resultSet.next()) {
            return (resultSet.getInt(1)+1);
        }
        return 1;
    }

    public static boolean save(Land land, List<LandDetail> landDetailList, List<Owner> ownerList) throws SQLException {

        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isLandSaved = CrudUtil.execute("INSERT INTO grama_vista.land (land_num, plan_num, land_area) VALUES (?,?,?)",
                    land.getLand_id(), land.getPlan_num(), land.getL_area());
            if (isLandSaved) {
                boolean isOwnerSaved = OwnerModel.save(ownerList);
                if (isOwnerSaved) {
                    boolean isDetailSaved = LandTypeModel.saveDetail(landDetailList);
                    if (isDetailSaved) {
                        con.commit();
                        return true;
                    }
                }
            }

            return false;
        } catch (SQLException er) {
            System.out.println(er);
            con.rollback();
            return false;
        } finally {

            con.setAutoCommit(true);
        }

    }
}
