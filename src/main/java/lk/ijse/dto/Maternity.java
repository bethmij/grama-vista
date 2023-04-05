package lk.ijse.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor

public class Maternity {
    private String ID;
    private String civil_ID;
    private String name;
    private LocalDate date;
    private String mid_wife;
}
