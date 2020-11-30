package ro.tuc.ds2020.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.dtos.validators.annotation.AgeLimit;
import ro.tuc.ds2020.entities.Account;
import ro.tuc.ds2020.entities.Patient;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class CaregiverDTO extends RepresentationModel<CaregiverDTO> {
    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @AgeLimit(limit = 18)
    private int age;
    @NotNull
    private String gender;

    private Account account;

    @JsonIgnore
    private List<Patient> patients;

    public CaregiverDTO(){}

    public CaregiverDTO(UUID id, @NotNull String name, @NotNull String address, int age, @NotNull String gender, Account account) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.account = account;
    }

    public CaregiverDTO(UUID id, @NotNull String name, @NotNull String address, int age, @NotNull String gender, Account account, List<Patient> patients) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.account = account;
        this.patients = patients;
    }

    public CaregiverDTO( @NotNull String name, @NotNull String address, int age, @NotNull String gender, Account account, List<Patient> patients) {

        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.account = account;
        this.patients = patients;
    }

    public CaregiverDTO(Link initialLink, UUID id, @NotNull String name, @NotNull String address, int age, @NotNull String gender, Account account, List<Patient> patients) {
        super(initialLink);
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.account = account;
        this.patients = patients;
    }

    public CaregiverDTO(Iterable<Link> initialLinks, UUID id, @NotNull String name, @NotNull String address, int age, @NotNull String gender, Account account, List<Patient> patients) {
        super(initialLinks);
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.account = account;
        this.patients = patients;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
