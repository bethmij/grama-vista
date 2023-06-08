package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor

public class VoteReg {
    private String election_id;
    private String election_type;
    private Integer candidate_count;
    private LocalDate date;
}
