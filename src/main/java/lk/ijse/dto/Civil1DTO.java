package lk.ijse.dto;
import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor



public class Civil1DTO {
    private String id;
    private String name;
    private String nic;
    private String address;
    private LocalDate dob;
    private String gender;
    private String marriage;
    private String relation;
    private String residence;
    private String email;
}
