package lk.ijse.dao;


import lk.ijse.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDAOFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        ADDCANDIDATEDAO, CANDIDATEDAO, CIVILDAO, CONTACTDAO, COOWNERDAO, DEADDAO, DETAILDAO
        , DISABLEDAO, DIVISIONDAO, LANDDAO, LANDDETAILDAO, LANDTYPEDAO, MATERNITYDAO, MULTIRESIDENCEDAO
        , QUERYDAO, RESIDENCEDAO, USERDAO, VOTEDAO, VOTEREGDAO
    }

    public <T extends SuperDAO> T getDAO(DAOTypes res) {
        switch (res) {
            case ADDCANDIDATEDAO:
                return (T) new AddCandidateDAOImpl();
            case CANDIDATEDAO:
                return (T) new CandidateDAOImpl();
            case CIVILDAO:
                return (T) new CivilDAOImpl();
            case CONTACTDAO:
                return (T) new ContactDAOImpl();
            case COOWNERDAO:
                return (T) new CoOwnerDAOImpl();
            case DEADDAO:
                return (T) new DeadDAOImpl();
            case DETAILDAO:
                return (T) new DetailDAOImpl();
            case DISABLEDAO:
                return (T) new DisableDAOImpl();
            case DIVISIONDAO:
                return (T) new DivisionDAOImpl();
            case LANDDAO:
                return (T) new LandDAOImpl();
            case LANDDETAILDAO:
                return (T) new LandDetailDAOImpl();
            case LANDTYPEDAO:
                return (T) new LandTypeDAOImpl();
            case MATERNITYDAO:
                return (T) new MaternityDAOImpl();
            case MULTIRESIDENCEDAO:
                return (T) new MultiResidenceDAOImpl();
            case QUERYDAO:
                return (T) new QueryDAOImpl();
            case RESIDENCEDAO:
                return (T) new ResidenceDAOImpl();
            case USERDAO:
                return (T) new UserDAOImpl();
            case VOTEDAO:
                return (T) new VoteDAOImpl();
            case VOTEREGDAO:
                return (T) new VoteRegDAOImpl();
            default:
                return null;
        }
    }


}
