package lk.ijse.entity;

import lombok.*;

@Data
@AllArgsConstructor

public class Division {

    private String division_id;
    private String name;
    private String div_Secretariat;
    private String admin_officer;
    private Double land_area;
    private Integer population;


}
