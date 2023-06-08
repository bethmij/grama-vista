package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Blob;

@Data
@AllArgsConstructor


public class Candidate {
    private String election_id;
    private String division_id;
    private String nic;
    private String name;
    private String political_party;
    private String address;
    private Integer contact;
    private Blob image;

}
