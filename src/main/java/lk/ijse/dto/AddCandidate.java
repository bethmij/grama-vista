package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor

public class AddCandidate {
    private String election_id;
    private String candidate_id;


}
