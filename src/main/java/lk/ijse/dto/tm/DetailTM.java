package lk.ijse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor

public class DetailTM {
        private String Function;
        private String User;
        private LocalTime Time;
        private LocalDate Date;
        private String Description;
}
