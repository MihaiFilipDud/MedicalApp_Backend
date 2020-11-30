package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.dtos.builders.MedicationPlanBuilder;
import ro.tuc.ds2020.dtos.builders.PatientBuilder;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.repositories.MedicationPlanRepository;
import ro.tuc.ds2020.repositories.MedicationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicationPlanService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicationPlanService.class);
    private final MedicationPlanRepository medicationPlanRepository;
    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationPlanService(MedicationPlanRepository medicationPlanRepository, MedicationRepository medicationRepository) {
        this.medicationPlanRepository = medicationPlanRepository;
        this.medicationRepository = medicationRepository;
    }

    public List<MedicationPlanDTO> findMedicationPlans() {
        List<MedicationPlan> medicationPlanList = medicationPlanRepository.findAll();
        return medicationPlanList.stream()
                .map(MedicationPlanBuilder::toMedicationPlanDTO)
                .collect(Collectors.toList());
    }

    public MedicationPlanDTO findMedicationPlanById(UUID id) {
        Optional<MedicationPlan> prosumerOptional = medicationPlanRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("medicationPlan with id {} was not found in db", id);
            throw new ResourceNotFoundException(MedicationPlan.class.getSimpleName() + " with id: " + id);
        }
        return MedicationPlanBuilder.toMedicationPlanDTO(prosumerOptional.get());
    }

    public UUID insert(MedicationPlanDTO medicationPlanDTO) {
        MedicationPlan medicationPlan = MedicationPlanBuilder.toEntity(medicationPlanDTO);
        List<Medication> meds = new ArrayList<Medication>();

        for(MedicationDTO m : medicationPlanDTO.getMedications()){
            Medication med = medicationRepository.findById(m.getId()).get();
            meds.add(med);
        }
        medicationPlan.setMedications(meds);
        medicationPlan = medicationPlanRepository.save(medicationPlan);
        LOGGER.debug("medicationPlan with id {} was inserted in db", medicationPlan.getId());
        System.out.println("test");
        return medicationPlan.getId();
    }

//    public medicationPlanDTO updatemedicationPlan(UUID id){
//        Optional<medicationPlan> prosumerOptional = medicationPlanRepository.update(id);
//        return null;
//    }

    public void deleteById(UUID id) {
        medicationPlanRepository.deleteById(id);
    }

    public UUID update(MedicationPlanDTO medicationPlanDTO){
        MedicationPlan medicationPlan = MedicationPlanBuilder.toEntity(medicationPlanDTO);
        List<Medication> meds = new ArrayList<Medication>();

        for(MedicationDTO m : medicationPlanDTO.getMedications()){
            Medication med = medicationRepository.findById(m.getId()).get();
            meds.add(med);
        }
        medicationPlan.setMedications(meds);
        medicationPlan.setId(medicationPlanDTO.getId());
        Optional<MedicationPlan> prosumerOptional = medicationPlanRepository.findById(medicationPlan.getId());
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("medicationPlan with id {} was not found in db", medicationPlan.getId());
            throw new ResourceNotFoundException(MedicationPlan.class.getSimpleName() + " with id: " + medicationPlan.getId());
        }

        medicationPlan = medicationPlanRepository.save(medicationPlan);
        LOGGER.debug("medicationPlan with id {} was updated in db", medicationPlan.getId());
        return medicationPlan.getId();
    }


    public List<MedicationPlanDTO> findMedicationPlansForPatient(UUID patientID) {
        List<MedicationPlan> medicationPlanList = medicationPlanRepository.findMedicationPlansByPatient_Id(patientID);
        return medicationPlanList.stream()
                .map(MedicationPlanBuilder::toMedicationPlanDTO)
                .collect(Collectors.toList());

    }



}
