package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor

public class LandDTO {
    private Integer land_id;
    private String plan_num;
    private Double l_area;
    private List<LandDetailDTO> landDetailList;
    private List<CoOwnerDTO> coOwnerLists;

    public LandDTO(Integer land_id, String plan_num, Double l_area) {
        this.land_id = land_id;
        this.plan_num = plan_num;
        this.l_area = l_area;
    }

    public List<LandDetailDTO> getLandDetailList() {
        return landDetailList;
    }

    public List<CoOwnerDTO> getCoOwnerLists() {
        return coOwnerLists;
    }


}
