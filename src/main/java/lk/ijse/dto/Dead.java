package lk.ijse.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Dead {
    private Integer ID;
    private String civil_ID;
    private String name;
    private Integer age;
    private LocalDate date;
}
