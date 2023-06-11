package lk.ijse.dto;
import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
@Data
@AllArgsConstructor


public class DeadDTO {

        private String dead_id;
        private String civil_id;
        private String name;
        private LocalDate date;
        private Integer age;

        private String division_id;

        public DeadDTO(String dead_id, String civil_id, String name, LocalDate date, Integer age) {
                this.dead_id = dead_id;
                this.civil_id = civil_id;
                this.name = name;
                this.date = date;
                this.age = age;
        }

        public String getDivision_id() {
                return division_id;
        }
}


