package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.LandBO;
import lk.ijse.dao.DAOFactory;
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

    LandDAO landDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.LANDDAO);
    LandTypeDAO landTypeDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.LANDTYPEDAO);
    DetailDAO detailDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DETAILDAO);
    CoOwnerDAO coOwnerDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.COOWNERDAO);
    LandDetailDAO landDetailDAO =DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.LANDDETAILDAO);

    @Override
    public Integer getNextLandID() throws SQLException {
        return landDAO.getNextLandId();
    }

    @Override
    public Integer getLandType(String id) throws SQLException {
        return landTypeDAO.getTypeId(id);
    }

    @Override
    public boolean saveLand(LandDTO landDTO) throws SQLException {
        Land land = new Land(landDTO.getLand_id(),landDTO.getPlan_num(),landDTO.getL_area());

        List<LandDetail> landDetails = new ArrayList<>();
        for (LandDetailDTO landDetailDTO : landDTO.getLandDetailList()) {
            landDetails.add(new LandDetail(landDetailDTO.getType_id(),landDetailDTO.getLand_num()));
        }

        List<CoOwner> coOwners = new ArrayList<>();
        for (CoOwnerDTO coOwnerList : landDTO.getCoOwnerLists()) {
            coOwners.add(new CoOwner(coOwnerList.getLand_id(),coOwnerList.getCivil_id(),coOwnerList.getLot_num(),coOwnerList.getPercentage()));
        }
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isLandSaved = landDAO.save (land);
            Integer id = landDAO.getLandNum(land.getPlan_num());
            if (isLandSaved) {

                for(int i = 0; i< coOwners.size(); i++){
                    coOwners.get(i).setLand_id(id);
                }
                boolean isOwnerSaved = coOwnerDAO.save(coOwners);
                if (isOwnerSaved) {

                    for(int i=0; i<landDTO.getLandDetailList().size(); i++){
                        landDTO.getLandDetailList().get(i).setLand_num(id);
                    }
                    boolean isDetailSaved = landDetailDAO.save(landDetails);
                    if (isDetailSaved) {
                        con.commit();
                        return true;
                    }
                }
            }

            return false;
        } catch (SQLException | ClassNotFoundException er) {
            System.out.println(er);
            con.rollback();
            return false;
        } finally {

            con.setAutoCommit(true);
        }
    }

    @Override
    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }

    @Override
    public boolean updateLand(LandDTO landDTO ) throws SQLException {
        Land land = new Land(landDTO.getLand_id(),landDTO.getPlan_num(),landDTO.getL_area());

        List<LandDetail> landDetails = new ArrayList<>();
        for (LandDetailDTO landDetailDTO : landDTO.getLandDetailList()) {
            landDetails.add(new LandDetail(landDetailDTO.getType_id(),landDetailDTO.getLand_num()));
        }

        List<CoOwner> coOwners = new ArrayList<>();
        for (CoOwnerDTO coOwnerList : landDTO.getCoOwnerLists()) {
            coOwners.add(new CoOwner(coOwnerList.getLand_id(),coOwnerList.getCivil_id(),coOwnerList.getLot_num(),coOwnerList.getPercentage()));
        }
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isLandUpdated = landDAO.update (land);
            Integer id = landDAO.getLandNum(land.getPlan_num());
            if (isLandUpdated) {

                for(int i = 0; i< landDTO.getCoOwnerLists().size(); i++){
                    landDTO.getCoOwnerLists().get(i).setLand_id(id);
                }

                boolean isOwnerUpdated = coOwnerDAO.save(coOwners);
                if (isOwnerUpdated) {

                    for(int i=0; i<landDTO.getLandDetailList().size(); i++){
                        landDTO.getLandDetailList().get(i).setLand_num(id);
                    }

                    boolean isDetailUpdated = landDetailDAO.save(landDetails);
                    if (isDetailUpdated) {
                        con.commit();
                        return true;
                    }
                }
            }

            return false;
        } catch (SQLException | ClassNotFoundException er) {
            System.out.println(er);
            con.rollback();
            return false;
        } finally {

            con.setAutoCommit(true);
        }

    }
}
