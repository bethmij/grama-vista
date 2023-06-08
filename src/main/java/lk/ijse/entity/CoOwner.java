package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class CoOwner {
    private Integer land_id;
    private String civil_id;
    private String lot_num;
    private Double percentage;
}
