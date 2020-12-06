package ro.tuc.ds2020.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
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

    @Column(name = "intake_interval_start")
    private Time intakeIntervalStart;

    @Column(name = "intake_interval_end")
    private Time intakeIntervalEnd;

    @Column(name = "treatment_start")
    private Date treatmentStart;

    @Column(name = "treatment_end")
    private Date treatmentEnd;


    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "patient_id")
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

    public MedicationPlan(Time intakeStart, Time intakeEnd, Date treatmentStart, Date treatmentEnd, Patient patient, List<Medication> medications){
        this.intakeIntervalStart = intakeStart;
        this.intakeIntervalEnd = intakeEnd;
        this.treatmentStart = treatmentStart;
        this.treatmentEnd = treatmentEnd;
        this.patient = patient;
        this.medications = medications;
    }

    public MedicationPlan(Time intakeStart, Time intakeEnd, Date treatmentStart, Date treatmentEnd){
        this.intakeIntervalStart = intakeStart;
        this.intakeIntervalEnd = intakeEnd;
        this.treatmentStart = treatmentStart;
        this.treatmentEnd = treatmentEnd;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Time getIntakeIntervalStart() {
        return intakeIntervalStart;
    }

    public void setIntakeIntervalStart(Time intakeIntervalStart) {
        this.intakeIntervalStart = intakeIntervalStart;
    }

    public Time getIntakeIntervalEnd() {
        return intakeIntervalEnd;
    }

    public void setIntakeIntervalEnd(Time intakeIntervalEnd) {
        this.intakeIntervalEnd = intakeIntervalEnd;
    }

    public Date getTreatmentStart() {
        return treatmentStart;
    }

    public void setTreatmentStart(Date treatmentStart) {
        this.treatmentStart = treatmentStart;
    }

    public Date getTreatmentEnd() {
        return treatmentEnd;
    }

    public void setTreatmentEnd(Date treatmentEnd) {
        this.treatmentEnd = treatmentEnd;
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
}
