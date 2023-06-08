package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ElecCandidateEntity {
    private String  election_id;
    private String candidate_id;
    private String name;
    private String politic;
}
