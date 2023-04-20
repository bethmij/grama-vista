package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Vote {
    private String election_id;
    private String election_type;
    private Integer candidate_count;
}
