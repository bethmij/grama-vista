package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class AddCandidateDTO {
    private String election_id;
    private String candidate_id;


}
