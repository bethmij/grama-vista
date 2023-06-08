package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor

public class VoteDTO {
    private String election_id;
    private String election_type;
    private Integer candidate_count;
    private LocalDate date;
}
