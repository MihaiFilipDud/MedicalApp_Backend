package ro.tuc.ds2020.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.Patient;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;


//@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class MedicationPlanDTO {


    private UUID id;

    @NotNull
    private String dailyInatkes;

    @NotNull
    private String treatmentPeriod;

    //@JsonIgnore
    private Patient patient;


    private List<MedicationDTO> medications;



    public MedicationPlanDTO(@NotNull UUID id, @NotNull String dailyInatkes, @NotNull String treatmentPeriod, Patient patient, List<MedicationDTO> medications) {
        this.id = id;
        this.dailyInatkes = dailyInatkes;
        this.treatmentPeriod = treatmentPeriod;
        this.patient = patient;
        this.medications = medications;
    }

    public MedicationPlanDTO(@NotNull UUID id, @NotNull String dailyInatkes, @NotNull String treatmentPeriod) {
        this.id = id;
        this.dailyInatkes = dailyInatkes;
        this.treatmentPeriod = treatmentPeriod;
    }

    public MedicationPlanDTO(@NotNull UUID id, @NotNull String dailyInatkes, @NotNull String treatmentPeriod, Patient patient) {
        this.id = id;
        this.dailyInatkes = dailyInatkes;
        this.treatmentPeriod = treatmentPeriod;
        this.patient = patient;
    }

    public MedicationPlanDTO(@NotNull UUID id, @NotNull String dailyInatkes, @NotNull String treatmentPeriod, List<MedicationDTO> medications) {
        this.id = id;
        this.dailyInatkes = dailyInatkes;
        this.treatmentPeriod = treatmentPeriod;
        this.medications = medications;
    }

    public MedicationPlanDTO(@NotNull String dailyInatkes, @NotNull String treatmentPeriod, Patient patient, List<MedicationDTO> medications) {
        this.dailyInatkes = dailyInatkes;
        this.treatmentPeriod = treatmentPeriod;
        this.patient = patient;
        this.medications = medications;
    }

    public MedicationPlanDTO() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDailyInatkes() {
        return dailyInatkes;
    }

    public void setDailyInatkes(String dailyInatkes) {
        this.dailyInatkes = dailyInatkes;
    }

    public String getTreatmentPeriod() {
        return treatmentPeriod;
    }

    public void setTreatmentPeriod(String treatmentPeriod) {
        this.treatmentPeriod = treatmentPeriod;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<MedicationDTO> getMedications() {
        return medications;
    }

    public void setMedications(List<MedicationDTO> medications) {
        this.medications = medications;
    }
}
