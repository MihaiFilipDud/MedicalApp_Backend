package ro.tuc.ds2020.hessian_rmi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.entities.MedicationStatus;
import ro.tuc.ds2020.repositories.MedicationPlanRepository;
import ro.tuc.ds2020.repositories.MedicationRepository;
import ro.tuc.ds2020.repositories.MedicationStatusRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class MedicationPlanTransferImpl implements MedicationPlanTransfer {

    @Autowired
    private final MedicationPlanRepository medicationPlanRepository;
    private final MedicationStatusRepository medicationStatusRepository;
    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationPlanTransferImpl(MedicationPlanRepository medicationPlanRepository, MedicationStatusRepository medicationStatusRepository, MedicationRepository medicationRepository) {
        this.medicationPlanRepository = medicationPlanRepository;
        this.medicationStatusRepository = medicationStatusRepository;
        this.medicationRepository = medicationRepository;
    }

    @Override
    public List<MedicationPlan> transferMedPlan(UUID id) {
        List<MedicationPlan> medicationPlanList = medicationPlanRepository.findMedicationPlansByPatient_Id(id);
        for(MedicationPlan medicationPlan:medicationPlanList){
            medicationPlan.setPatient(null);
        }

        List<MedicationPlan> activeMedPlans = new ArrayList<MedicationPlan>();

        Date curDate = new Date();
        for (MedicationPlan medicationPlan: medicationPlanList){
            //System.out.println(curDate.getTime()+"  "+medicationPlan.getTreatmentStart().getTime()+"  "+medicationPlan.getTreatmentEnd().getTime());
            if(curDate.getTime() > medicationPlan.getTreatmentStart().getTime() && curDate.getTime() < medicationPlan.getTreatmentEnd().getTime()){
                activeMedPlans.add(medicationPlan);
            }
        }

        for(MedicationPlan medicationPlan: activeMedPlans){
            for(Medication medication: medicationPlan.getMedications()){
                medication.setMedicationPlan(null);
                medication.setMedicationStatuses(null);
            }
        }

        return activeMedPlans;
    }

    @Override
    public void medicationStatusResponse(MedicationStatus medicationStatus) {
        MedicationStatus medStatus = new MedicationStatus(medicationStatus.getAdministeredTime(), medicationStatus.getTaken(), medicationStatus.getMedication());
        //System.out.println(medStatus);
        System.out.println(medicationStatusRepository.save(medStatus).toString());
        //medicationStatusRepository.save(new MedicationStatus());
    }

    public MedicationPlanRepository getMedicationPlanRepository() {
        return medicationPlanRepository;
    }

    public MedicationStatusRepository getMedicationStatusRepository() {
        return medicationStatusRepository;
    }
}
