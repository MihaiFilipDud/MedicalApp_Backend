package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.MedicationPlan;

import java.util.ArrayList;
import java.util.List;

public class MedicationPlanBuilder {

    public static MedicationPlanDTO toMedicationPlanDTO(MedicationPlan medicationPlan) {

        List<MedicationDTO> meds = new ArrayList<MedicationDTO>();
        for(Medication m : medicationPlan.getMedications()){
            meds.add(MedicationBuilder.toMedicationDTO(m));
        }

        return new MedicationPlanDTO(medicationPlan.getId(),
                medicationPlan.getIntakeIntervalStart(),
                medicationPlan.getIntakeIntervalEnd(),
                medicationPlan.getTreatmentStart(),
                medicationPlan.getIntakeIntervalEnd(),
                meds);
    }

    public static MedicationPlan toEntity(MedicationPlanDTO medicationPlanDTO) {
        List<Medication> meds = new ArrayList<Medication>();
        if(medicationPlanDTO.getMedications() == null){
            medicationPlanDTO.setMedications(new ArrayList<MedicationDTO>());
        }
        for(MedicationDTO m : medicationPlanDTO.getMedications()){
            meds.add(MedicationBuilder.toEntity(m));
        }
        return new MedicationPlan(medicationPlanDTO.getIntakeIntervalStart(),
                medicationPlanDTO.getIntakeIntervalEnd(),
                medicationPlanDTO.getTreatmentStart(),
                medicationPlanDTO.getTreatmentEnd(),
                medicationPlanDTO.getPatient(),
                meds);
    }
}
