package lk.ijse.model;

import lk.ijse.db.DBConnection;
import lk.ijse.dto.Land;
import lk.ijse.dto.LandType;
import lk.ijse.dto.Owner;
import lk.ijse.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LandModel {
    public static String getNextLandId() throws SQLException {


        ResultSet resultSet = CrudUtil.execute("SELECT land_num FROM grama_vista.land ORDER BY land_num DESC LIMIT 1" );

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("L00");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "L00" + id;
        }
        return "L001";
    }


    public static boolean save(Land land, List<LandType> landTypeList, List<Owner> ownerList) throws SQLException {

        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isLandSaved = CrudUtil.execute("INSERT INTO grama_vista.land (land_num, plan_num, land_area) VALUES (?,?,?)",
                    land.getLand_id(), land.getPlan_num(), land.getL_area());
            if (isLandSaved) {

                boolean isTypeSaved = LandTypeModel.save(landTypeList);
                if (isTypeSaved) {
                    boolean isOwnerSaved = OwnerModel.save(ownerList);
                    if (isOwnerSaved) {
                        boolean isDetailSaved = LandTypeModel.saveDetail(landTypeList);
                        if (isDetailSaved) {
                            con.commit();
                            return true;
                        }
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
