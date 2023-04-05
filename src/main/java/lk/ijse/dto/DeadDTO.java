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


}
