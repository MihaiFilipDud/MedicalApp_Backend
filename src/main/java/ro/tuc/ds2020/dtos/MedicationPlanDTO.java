package ro.tuc.ds2020.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.Patient;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.UUID;


//@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class MedicationPlanDTO {


    private UUID id;

    private Time intakeIntervalStart;

    private Time intakeIntervalEnd;

    private Date treatmentStart;

    private Date treatmentEnd;

    private Patient patient;

    private List<MedicationDTO> medications;


    public MedicationPlanDTO(UUID id, Time intakeIntervalStart, Time intakeIntervalEnd, Date treatmentStart, Date treatmentEnd, Patient patient, List<MedicationDTO> medications) {
        this.id = id;
        this.intakeIntervalStart = intakeIntervalStart;
        this.intakeIntervalEnd = intakeIntervalEnd;
        this.treatmentStart = treatmentStart;
        this.treatmentEnd = treatmentEnd;
        this.patient = patient;
        this.medications = medications;
    }

    public MedicationPlanDTO(UUID id, Time intakeIntervalStart, Time intakeIntervalEnd, Date treatmentStart, Date treatmentEnd, List<MedicationDTO> medications) {
        this.id = id;
        this.intakeIntervalStart = intakeIntervalStart;
        this.intakeIntervalEnd = intakeIntervalEnd;
        this.treatmentStart = treatmentStart;
        this.treatmentEnd = treatmentEnd;
        this.medications = medications;
    }

    public MedicationPlanDTO() {
    }

//    public MedicationPlanDTO(Time intakeIntervalStart, Time intakeIntervalEnd, Date treatmentStart, Date treatmentEnd, Patient patient, List<MedicationDTO> medications) {
//        this.intakeIntervalStart = intakeIntervalStart;
//        this.intakeIntervalEnd = intakeIntervalEnd;
//        this.treatmentStart = treatmentStart;
//        this.treatmentEnd = treatmentEnd;
//        this.patient = patient;
//        this.medications = medications;
//    }
//
//    public MedicationPlanDTO(Time intakeIntervalStart, Time intakeIntervalEnd, Date treatmentStart, Date treatmentEnd) {
//        this.intakeIntervalStart = intakeIntervalStart;
//        this.intakeIntervalEnd = intakeIntervalEnd;
//        this.treatmentStart = treatmentStart;
//        this.treatmentEnd = treatmentEnd;
//    }



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

    public List<MedicationDTO> getMedications() {
        return medications;
    }

    public void setMedications(List<MedicationDTO> medications) {
        this.medications = medications;
    }
}
