package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.LandManageBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CoOwnerDAO;
import lk.ijse.dao.custom.DetailDAO;
import lk.ijse.dao.custom.LandDAO;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.dao.custom.impl.*;
import lk.ijse.dto.CoOwnerDTO;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dto.LandDTO;
import lk.ijse.dto.LandDetailDTO;
import lk.ijse.entity.CoOwner;
import lk.ijse.entity.Detail;
import lk.ijse.entity.Land;
import lk.ijse.entity.LandDetailEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LandManageBOImpl implements LandManageBO {

    DetailDAO detailDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DETAILDAO);
    LandDAO landDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.LANDDAO);
    QueryDAO queryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);
    CoOwnerDAO coOwnerDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.COOWNERDAO);

    @Override
    public void saveDetail(DetailDTO detail) throws SQLException {
        Detail detail1 = new Detail(detail.getFunction_name(),detail.getUser(),detail.getTime(),detail.getDate(),detail.getDescription());
        detailDAO.save(detail1);
    }

    @Override
    public List<String> loadLandId() throws SQLException {
         return landDAO.loadLandID();
    }

    @Override
    public List<LandDTO> searchAllLand() throws SQLException {
        List<Land> land = landDAO.searchAll();
        List<LandDTO> landDTOS = new ArrayList<>();
        for (Land land1 : land) {
            landDTOS.add(new LandDTO(land1.getLand_id(),land1.getPlan_num(),land1.getL_area()));
        }
        return landDTOS;
    }

    @Override
    public List<LandDetailDTO> searchAllLandDetail() throws SQLException {
        List<LandDetailEntity> landDetail = queryDAO.searchAllLandDetail();
        List<LandDetailDTO> landDetailDTOS = new ArrayList<>();
        for (LandDetailEntity land1 : landDetail) {
            landDetailDTOS.add(new LandDetailDTO(land1.getType_id(),land1.getLand_num(),land1.getLand_type()));
        }
        return landDetailDTOS;
    }

    @Override
    public List<CoOwnerDTO> searchAllOwners() throws SQLException {
        List<CoOwner> coOwnerLists = coOwnerDAO.searchAll();
        List<CoOwnerDTO> coOwnerDTOS = new ArrayList<>();
        for (CoOwner coOwnerList : coOwnerLists) {
            coOwnerDTOS.add(new CoOwnerDTO(coOwnerList.getLand_id(),coOwnerList.getCivil_id(),coOwnerList.getLot_num(),coOwnerList.getPercentage()));
        }
        return coOwnerDTOS;
    }

    @Override
    public LandDTO searchLand(String id) throws SQLException, ClassNotFoundException {
        Land land = landDAO.search(id);
        LandDTO landDTOS = new LandDTO(land.getLand_id(),land.getPlan_num(),land.getL_area());
        return landDTOS;
    }

    @Override
    public List<LandDetailDTO> searchLandDetail(String id) throws SQLException {
        List<LandDetailEntity> landDetail = queryDAO.searchLandDetail(id);
        List<LandDetailDTO> landDetailDTOS = new ArrayList<>();
        for (LandDetailEntity land1 : landDetail) {
            landDetailDTOS.add(new LandDetailDTO(land1.getType_id(),land1.getLand_num(),land1.getLand_type()));
        }
        return landDetailDTOS;
    }

    @Override
    public List<CoOwnerDTO> searchOwners(String id) throws SQLException, ClassNotFoundException {
        List<CoOwner> coOwnerLists = coOwnerDAO.searchOwner(id);
        List<CoOwnerDTO> coOwnerDTOS = new ArrayList<>();
        for (CoOwner coOwnerList : coOwnerLists) {
            coOwnerDTOS.add(new CoOwnerDTO(coOwnerList.getLand_id(),coOwnerList.getCivil_id(),coOwnerList.getLot_num(),coOwnerList.getPercentage()));
        }
        return coOwnerDTOS;
    }

    @Override
    public boolean deleteLand(String id) throws SQLException, ClassNotFoundException {
      return landDAO.delete(id);
    }
}
