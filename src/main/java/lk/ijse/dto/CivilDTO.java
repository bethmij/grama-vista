package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Blob;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor

public class CivilDTO {
    String iD;
    Blob image;
    String name;
    String nic;
    String address;
    LocalDate dob;
    Integer age;
    String gender;
    String marriage;
    String relation;
    String education;
    String school;
    String occupation;
    String work;
    Double salary;
    String email;
    List<ContactDTO> contactList;
    List<MultiResidenceDTO> residenceList;

    public CivilDTO(String iD, Blob image, String name, String nic, String address, LocalDate dob, Integer age, String gender, String marriage, String relation, String education, String school, String occupation, String work, Double salary, String email) {
        this.iD = iD;
        this.image = image;
        this.name = name;
        this.nic = nic;
        this.address = address;
        this.dob = dob;
        this.age = age;
        this.gender = gender;
        this.marriage = marriage;
        this.relation = relation;
        this.education = education;
        this.school = school;
        this.occupation = occupation;
        this.work = work;
        this.salary = salary;
        this.email = email;
    }/*

    public CivilDTO(String iD, Blob image, String name, String nic, String address, LocalDate dob, Integer age, String gender, String marriage, String relation, String education, String school, String occupation, String work, Double salary, String email, List<ContactDTO> contactList, List<MultiResidenceDTO> residenceList ) {
        this.iD = iD;
        this.image = image;
        this.name = name;
        this.nic = nic;
        this.address = address;
        this.dob = dob;
        this.age = age;
        this.gender = gender;
        this.marriage = marriage;
        this.relation = relation;
        this.education = education;
        this.school = school;
        this.occupation = occupation;
        this.work = work;
        this.salary = salary;
        this.email = email;
        this.contactList = contactList;
        this.residenceList = residenceList;
    }*/

    public List<ContactDTO> getContactLists() {
        return contactList;
    }


    public List<MultiResidenceDTO> getResidenceLists() {
        return residenceList;
    }
}
