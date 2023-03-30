package lk.ijse.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor

public class Dead {
    private Integer ID;
    private String civil_ID;
    private String name;
    private Integer age;
    private LocalDate date;
}
