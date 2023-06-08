package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.LandBO;
import lk.ijse.dao.custom.*;
import lk.ijse.dao.custom.impl.*;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.CoOwnerDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.LandDTO;
import lk.ijse.dto.LandDetailDTO;
import lk.ijse.entity.CoOwner;
import lk.ijse.entity.Detail;
import lk.ijse.entity.Land;
import lk.ijse.entity.LandDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LandBOImpl implements LandBO {
    LandDAO landDAO = new LandDAOImpl();
    LandTypeDAO landTypeDAO = new LandTypeDAOImpl();
    DetailDAO detailDAO = new DetailDAOImpl();
    CoOwnerDAO coOwnerDAO = new CoOwnerDAOImpl();
    LandDetailDAO landDetailDAO =new LandDetailDAOImpl();

    public Integer getNextLandID() throws SQLException {
        return landDAO.getNextLandId();
    }

    public Integer getLandType(String id) throws SQLException {
        return landTypeDAO.getTypeId(id);
    }

    public boolean saveLand(LandDTO landDTO , List<LandDetailDTO> landDetailList, List<CoOwnerDTO> coOwnerLists) throws SQLException {
        Land land = new Land(landDTO.getLand_id(),landDTO.getPlan_num(),landDTO.getL_area());

        List<LandDetail> landDetails = new ArrayList<>();
        for (LandDetailDTO landDetailDTO : landDetailList) {
            landDetails.add(new LandDetail(landDetailDTO.getType_id(),landDetailDTO.getLand_num()));
        }

        List<CoOwner> coOwners = new ArrayList<>();
        for (CoOwnerDTO coOwnerList : coOwnerLists) {
            coOwners.add(new CoOwner(coOwnerList.getLand_id(),coOwnerList.getCivil_id(),coOwnerList.getLot_num(),coOwnerList.getPercentage()));
        }
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isLandSaved = landDAO.saveLand (land);
            Integer id = landDAO.getLandNum(land.getPlan_num());
            if (isLandSaved) {

                for(int i = 0; i< coOwners.size(); i++){
                    coOwners.get(i).setLand_id(id);
                }
                boolean isOwnerSaved = coOwnerDAO.save(coOwners);
                if (isOwnerSaved) {

                    for(int i=0; i<landDetailList.size(); i++){
                        landDetailList.get(i).setLand_num(id);
                    }
                    boolean isDetailSaved = landDetailDAO.saveDetail(landDetails);
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

    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }

    public boolean updateLand(LandDTO landDTO , List<LandDetailDTO> landDetailList, List<CoOwnerDTO> coOwnerLists) throws SQLException {
        Land land = new Land(landDTO.getLand_id(),landDTO.getPlan_num(),landDTO.getL_area());

        List<LandDetail> landDetails = new ArrayList<>();
        for (LandDetailDTO landDetailDTO : landDetailList) {
            landDetails.add(new LandDetail(landDetailDTO.getType_id(),landDetailDTO.getLand_num()));
        }

        List<CoOwner> coOwners = new ArrayList<>();
        for (CoOwnerDTO coOwnerList : coOwnerLists) {
            coOwners.add(new CoOwner(coOwnerList.getLand_id(),coOwnerList.getCivil_id(),coOwnerList.getLot_num(),coOwnerList.getPercentage()));
        }
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isLandUpdated = landDAO.updateLand (land);
            Integer id = landDAO.getLandNum(land.getPlan_num());
            if (isLandUpdated) {

                for(int i = 0; i< coOwnerLists.size(); i++){
                    coOwnerLists.get(i).setLand_id(id);
                }

                boolean isOwnerUpdated = coOwnerDAO.save(coOwners);
                if (isOwnerUpdated) {

                    for(int i=0; i<landDetailList.size(); i++){
                        landDetailList.get(i).setLand_num(id);
                    }

                    boolean isDetailUpdated = landDetailDAO.saveDetail(landDetails);
                    if (isDetailUpdated) {
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
