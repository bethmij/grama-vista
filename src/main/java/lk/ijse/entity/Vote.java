package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor

public class Vote {
    private String election_id;
    private String civil_id;
    private String candidate_id;
    private Date voted_date;
}
