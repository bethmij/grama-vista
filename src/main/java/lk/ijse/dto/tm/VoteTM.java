package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor

public class VoteTM {
    private String election_id;
    private String election_type;
    private LocalDate date;
    private Integer candidate_count;
    private Button view;
    private Button delete;
}
