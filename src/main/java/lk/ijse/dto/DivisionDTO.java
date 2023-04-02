package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class DivisionDTO {
    private String division_id;
    private String name;
    private String div_Secretariat;
    private String admin_officer;
    private Integer population;
    private Double land_area;
}
