package lk.ijse.dto;
import lombok.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString



public class Civil1 {
    private String name;
    private String nic;
    private String address;
    private LocalDate dob;
    private String gender;
    private String marriage;
    private String relation;
}