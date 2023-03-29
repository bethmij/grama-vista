package lk.ijse.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Maternity {
    private Integer ID;
    private String civil_ID;
    private LocalDate date;
    private Integer months;
    private String mid_wife;
}
