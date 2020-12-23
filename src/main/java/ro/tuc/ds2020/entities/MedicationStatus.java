package ro.tuc.ds2020.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class MedicationStatus {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    @Column
    private Date administeredTime;

    @Column
    private Boolean isTaken;

    @ManyToOne
    @JoinColumn(name = "med_id", referencedColumnName = "id")
    private Medication medication;


    public MedicationStatus() {
    }

    public MedicationStatus(Date administeredTime, Boolean isTaken, Medication medication){
        this.administeredTime = administeredTime;
        this.isTaken = isTaken;
        this.medication = medication;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getAdministeredTime() {
        return administeredTime;
    }

    public void setAdministeredTime(Date administeredTime) {
        this.administeredTime = administeredTime;
    }

    public Boolean getTaken() {
        return isTaken;
    }

    public void setTaken(Boolean taken) {
        isTaken = taken;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    @Override
    public String toString() {
        return "MedicationStatus{" +
                "id=" + id +
                ", administeredTime=" + administeredTime +
                ", isTaken=" + isTaken +
                ", medication=" + medication +
                '}';
    }
}
