package lk.ijse.entity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor


public class DeadEntity {

        private String dead_id;
        private String civil_id;
        private String name;
        private LocalDate date;
        private Integer age;


}
