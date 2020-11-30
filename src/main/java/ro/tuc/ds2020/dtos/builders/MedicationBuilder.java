package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.entities.Medication;

public class MedicationBuilder {

    public static MedicationDTO toMedicationDTO(Medication medication) {
        return new MedicationDTO(medication.getId(),
                medication.getName(),
                medication.getSideEffects(),
                medication.getDosage());
    }

    public static Medication toEntity(MedicationDTO medicationDTO) {
        return new Medication(medicationDTO.getName(),
                medicationDTO.getSideEffects(),
                medicationDTO.getDosage(),
                medicationDTO.getMedicationPlan());
    }
}
