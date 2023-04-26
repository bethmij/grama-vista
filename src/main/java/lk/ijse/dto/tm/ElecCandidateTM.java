package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor

public class ElecCandidateTM {
       private String election_id;
       private String candidate_id;
       private String name;
       private String politic;
       private Button delete;
}
