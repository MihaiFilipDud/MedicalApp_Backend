package ro.tuc.ds2020.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity(name = "medication")
public class Medication implements Serializable {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;


    @Column
    private String name;

    @Column
    private String sideEffects;

    @Column
    private String dosage;


    @ManyToMany(mappedBy = "medications",cascade = CascadeType.ALL)
    //@JsonIgnore
    private List<MedicationPlan> medicationPlan;

    public Medication() {
    }

    public Medication(String name, String sideEffects, String dosage) {
        this.name = name;
        this.sideEffects = sideEffects;
        this.dosage = dosage;
    }

    public Medication(String name, String sideEffects, String dosage, List<MedicationPlan> medicationPlan) {
        this.name = name;
        this.sideEffects = sideEffects;
        this.dosage = dosage;
        this.medicationPlan = medicationPlan;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<MedicationPlan> getMedicationPlan() {
        return medicationPlan;
    }

    public void setMedicationPlan(List<MedicationPlan> medicationPlan) {
        this.medicationPlan = medicationPlan;
    }
}
