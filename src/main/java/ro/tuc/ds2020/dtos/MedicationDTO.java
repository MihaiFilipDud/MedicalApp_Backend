package ro.tuc.ds2020.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ro.tuc.ds2020.entities.MedicationPlan;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class MedicationDTO {

    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private String sideEffects;

    @NotNull
    private String dosage;

    @JsonIgnore
    private List<MedicationPlan> medicationPlan;

    public MedicationDTO() {
    }

    public MedicationDTO(UUID id, @NotNull String name, @NotNull String sideEffects, @NotNull String dosage) {
        this.id = id;
        this.name = name;
        this.sideEffects = sideEffects;
        this.dosage = dosage;
    }

    public MedicationDTO(@NotNull String name, @NotNull String sideEffects, @NotNull String dosage) {
        this.name = name;
        this.sideEffects = sideEffects;
        this.dosage = dosage;
    }

    public MedicationDTO(UUID id, @NotNull String name, @NotNull String sideEffects, @NotNull String dosage, List<MedicationPlan> medicationPlan) {
        this.id = id;
        this.name = name;
        this.sideEffects = sideEffects;
        this.dosage = dosage;
        this.medicationPlan = medicationPlan;
    }

    public MedicationDTO(@NotNull String name, @NotNull String sideEffects, @NotNull String dosage, List<MedicationPlan> medicationPlan) {
        this.name = name;
        this.sideEffects = sideEffects;
        this.dosage = dosage;
        this.medicationPlan = medicationPlan;
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

    public List<MedicationPlan> getMedicationPlan() {
        return medicationPlan;
    }

    public void setMedicationPlan(List<MedicationPlan> medicationPlan) {
        this.medicationPlan = medicationPlan;
    }
}
