package ro.tuc.ds2020.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity(name = "medicationPlan")
//@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class MedicationPlan implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "daily_intakes")
    private String dailyInatkes;

    @Column(name = "treatment_period")
    private String treatmentPeriod;

    @Column(name = "")

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "patient_id")
    //@JsonBackReference
    //@JsonIgnore
    private Patient patient;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(
            name = "medPlan_meds",
            joinColumns = @JoinColumn(name="medPlan_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id")
    )
    private List<Medication> medications;

    public MedicationPlan() {
    }


    public MedicationPlan(String dailyInatkes, String treatmentPeriod, Patient patient, List<Medication> medications) {
        this.dailyInatkes = dailyInatkes;
        this.treatmentPeriod = treatmentPeriod;
        this.patient = patient;
        this.medications = medications;
    }

    public MedicationPlan(String dailyInatkes, String treatmentPeriod) {
        this.dailyInatkes = dailyInatkes;
        this.treatmentPeriod = treatmentPeriod;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
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
}
