package ro.tuc.ds2020.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Caregiver extends Person implements Serializable {

    @OneToMany(mappedBy = "caregiver", cascade = CascadeType.PERSIST)

    private List<Patient> patients  = null;

    public Caregiver() {
    }

    public Caregiver(List<Patient> patients) {
        this.patients = patients;
    }

    public Caregiver(String name, String address, int age, List<Patient> patients) {
        super(name, address, age);
        this.patients = patients;
    }

    public Caregiver(String name, String address, int age, String gender, Account account, List<Patient> patients) {
        super(name, address, age, gender, account);
        this.patients = patients;
    }

    public Caregiver(String name, String address, int age, String gender, List<Patient> patients) {
        super(name, address, age, gender);
        this.patients = patients;
    }

    public Caregiver(String name, String address, int age, String gender, Account account) {
        super(name, address, age, gender, account);
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }


}
