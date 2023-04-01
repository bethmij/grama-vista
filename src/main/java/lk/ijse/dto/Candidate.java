package lk.ijse.dto;

import lombok.*;

import java.security.SecureRandom;

@Data
@AllArgsConstructor


public class Candidate {
    private String election_id;
    private String division_id;
    private String nic;
    private String name;
    private String political_party;
    private String gender;
    private String address;
    private Integer contact;

}
