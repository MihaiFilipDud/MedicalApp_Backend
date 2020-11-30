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



public class PatientDTO extends RepresentationModel<PatientDTO> {
    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @AgeLimit(limit = 18)
    private int age;
    @NotNull
    private String gender;

    private String medicalRecord;

    private Account account;

    //@JsonManagedReference
    @JsonIgnore
    private List<MedicationPlan> medPlans;

    @JsonIgnore
    private Caregiver caregiver;

    public PatientDTO() {
    }


    public PatientDTO(String name, String address, int age, String gender, String medicalRecord, Account account) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.medicalRecord = medicalRecord;
        this.account = account;
    }

    public PatientDTO(UUID id, String name, String address, int age, String gender, String medicalRecord, Account account) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.medicalRecord = medicalRecord;
        this.account = account;
    }

    public PatientDTO(String name, String address, int age, String gender, String medicalRecord) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.medicalRecord = medicalRecord;

    }

    public PatientDTO(UUID id, String name, String address, int age, String gender, String medicalRecord) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.medicalRecord = medicalRecord;

    }

    public PatientDTO(UUID id, @NotNull String name, @NotNull String address, int age, @NotNull String gender, String medicalRecord, Account account, List<MedicationPlan> medPlans, Caregiver caregiver) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.medicalRecord = medicalRecord;
        this.account = account;
        this.medPlans = medPlans;
        this.caregiver = caregiver;
    }

    public PatientDTO(@NotNull String name, @NotNull String address, int age, @NotNull String gender, String medicalRecord, Account account, List<MedicationPlan> medPlans, Caregiver caregiver) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.medicalRecord = medicalRecord;
        this.account = account;
        this.medPlans = medPlans;
        this.caregiver = caregiver;
    }

    public PatientDTO(@NotNull String name, @NotNull String address, int age, @NotNull String gender, String medicalRecord, Account account, Caregiver caregiver) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.medicalRecord = medicalRecord;
        this.account = account;
        this.caregiver = caregiver;
    }

    public PatientDTO(@NotNull String name, @NotNull String address, int age, @NotNull String gender, String medicalRecord, Account account, List<MedicationPlan> medPlans) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.medicalRecord = medicalRecord;
        this.account = account;
        this.medPlans = medPlans;
    }

    public PatientDTO(UUID id, @NotNull String name, @NotNull String address, int age, @NotNull String gender, String medicalRecord, Account account, List<MedicationPlan> medPlans) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.medicalRecord = medicalRecord;
        this.account = account;
        this.medPlans = medPlans;
    }

    @Override
    public String toString() {
        return "PatientDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", medicalRecord='" + medicalRecord + '\'' +
                ", account=" + account +
                ", medPlans=" + medPlans +
                ", caregiver=" + caregiver +
                '}';
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

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<MedicationPlan> getMedPlans() {
        return medPlans;
    }

    public void setMedPlans(List<MedicationPlan> medPlans) {
        this.medPlans = medPlans;
    }

    public Caregiver getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
    }
}
