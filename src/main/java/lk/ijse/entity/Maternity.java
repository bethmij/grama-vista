package lk.ijse.entity;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor

public class Maternity {
    private String ID;
    private String civil_ID;
    private Integer months;
    private String mid_wife;
}
