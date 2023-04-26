package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ElecCandidate {
    private String  election_id;
    private String candidate_id;
    private String name;
    private String politic;
}
