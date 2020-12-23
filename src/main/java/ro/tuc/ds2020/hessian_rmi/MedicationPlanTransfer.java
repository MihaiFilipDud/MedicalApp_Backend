package ro.tuc.ds2020.hessian_rmi;

import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.entities.MedicationStatus;

import java.util.List;
import java.util.UUID;

public interface MedicationPlanTransfer {

    public List<MedicationPlan> transferMedPlan(UUID id);

    public void medicationStatusResponse(MedicationStatus medicationStatus);
}
