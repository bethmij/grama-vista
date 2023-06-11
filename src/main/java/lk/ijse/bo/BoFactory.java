package lk.ijse.bo;

import lk.ijse.bo.custom.DetailFormBO;
import lk.ijse.bo.custom.impl.*;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {

    }

    public static BoFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BoFactory() : boFactory;
    }

    public enum BOTypes {
        ABOUTUSBO, ADDRESIDENCEBO, ADDLANDTYPEBO, CAMERABO, CANDIDATBO, CANDIDATEBO, CANDIDATEMANAGEBO, CANDIDATEVIEWBO, CIVILMANAGEBO,
        CIVILVIEWBO, DASHBOARDBO, DEADMANAGEBO, DEADPEOPLEBO, DETAILFORMBO, DISABLEMANAGEBO, DISABLEREGISTRATIONBO,
        DIVISIONMANAGEBO, DIVISIONREGISTRATIONBO, HOMEMANAGEBO, HOMEREGISTRATIONBO, INDIVIDUALBO , INDIVIDUALFORMBO, LANDBO,
        LANDMANAGEBO, LOGINBO, MANAGEBO, MATERNITYMANAGEBO, MATERNITYREGISTBO, OWNERSHIPBO, PASSWORDBO, REGISTRATIONBO,
        REPORTBO, SAMURDHIBO, USERMANAGEBO, USERREGISTRATIONBO, VOTEBO,VOTEEBO, VOTELOGINBO, VOTEMANAGEBO, VOTERESULTBO
    }

    public <T extends SuperBO > T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case ABOUTUSBO:
                return (T) new AboutUsBOImpl();
            case ADDRESIDENCEBO:
                return (T) new AddResidenceBOImpl();
            case ADDLANDTYPEBO:
                return (T) new AddLandTypeBOImpl();
            case CANDIDATEBO:
                return (T) new CandidateBOImpl();
            case CAMERABO:
                return (T) new CameraBOImpl();
            case CANDIDATBO:
                return (T) new CandidatBOImpl();
            case  CANDIDATEMANAGEBO:
                return (T) new CandidateManageBOImpl();
            case CANDIDATEVIEWBO:
                return (T) new CandidateViewBOImpl();
            case CIVILMANAGEBO:
                return (T) new CivilManageBOImpl();
            case CIVILVIEWBO:
                return (T) new CivilViewBOImpl();
            case DASHBOARDBO:
                return (T) new DashboardBOImpl();
            case DEADMANAGEBO:
                return (T) new DeadManageBOImpl();
            case DEADPEOPLEBO:
                return (T) new DeadPeopleBOImpl();
            case DETAILFORMBO:
                return (T) new DetailFormBOImpl();
            case DISABLEMANAGEBO:
                return (T) new DisableManageBOImpl();
            case DISABLEREGISTRATIONBO:
                return (T) new DisableRegistrationBOImpl();
            case DIVISIONMANAGEBO:
                return (T) new DivisionManageBOImpl();
            case DIVISIONREGISTRATIONBO:
                return (T) new DivisionRegistrationBOImpl();
            case HOMEMANAGEBO:
                return (T) new HomeManageBOImpl();
            case HOMEREGISTRATIONBO:
                return (T) new HomeRegistrationBOImpl();
            case INDIVIDUALBO:
                return (T) new IndividualBOImpl();
            case INDIVIDUALFORMBO:
                return (T) new IndividualFormBOImpl();
            case LANDBO:
                return (T) new LandBOImpl();
            case LANDMANAGEBO:
                return (T) new LandManageBOImpl();
            case LOGINBO:
                return (T) new LoginBOImpl();
            case MANAGEBO:
                return (T) new ManageBOImpl();
            case MATERNITYMANAGEBO:
                return (T) new MaternityManageBOImpl();
            case MATERNITYREGISTBO:
                return (T) new MaternityRegistBOImpl();
            case OWNERSHIPBO:
                return (T) new OwnershipBOImpl();
            case PASSWORDBO:
                return (T) new PasswordBOImpl();
            case REGISTRATIONBO:
                return (T) new RegistrationBOImpl();
            case REPORTBO:
                return (T) new ReportBOImpl();
            case SAMURDHIBO:
                return (T) new SamurdhiBOImpl();
            case USERMANAGEBO:
                return (T) new UserManageBOImpl();
            case USERREGISTRATIONBO:
                return (T) new UserRegistrationBOImpl();
            case VOTEBO:
                return (T) new VoteBOImpl();
            case VOTEEBO:
                return (T) new VoteeBOImpl();
            case VOTELOGINBO:
                return (T) new VoteLoginBOImpl();
            case VOTEMANAGEBO:
                return (T) new VoteManageBOImpl();
            case VOTERESULTBO:
                return (T) new VoteResultBOImpl();
            default:
                return null;
        }
    }

}
