package ro.tuc.ds2020.dtos;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.dtos.validators.annotation.AgeLimit;
import ro.tuc.ds2020.entities.Account;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.MedicationPlan;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;



public class DoctorDTO extends RepresentationModel<DoctorDTO> {
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



    public DoctorDTO() {
    }


    public DoctorDTO(UUID id, @NotNull String name, @NotNull String address, int age, @NotNull String gender, Account account) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.account = account;
    }

    public DoctorDTO(@NotNull String name, @NotNull String address, int age, @NotNull String gender, Account account) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.account = account;
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
}
