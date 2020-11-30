package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.dtos.builders.MedicationBuilder;
import ro.tuc.ds2020.dtos.builders.MedicationBuilder;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.repositories.MedicationRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicationService.class);
    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public List<MedicationDTO> findMedications() {
        List<Medication> medicationList = medicationRepository.findAll();
        return medicationList.stream()
                .map(MedicationBuilder::toMedicationDTO)
                .collect(Collectors.toList());
    }

    public MedicationDTO findMedicationById(UUID id) {
        Optional<Medication> prosumerOptional = medicationRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("medication with id {} was not found in db", id);
            throw new ResourceNotFoundException(Medication.class.getSimpleName() + " with id: " + id);
        }
        return MedicationBuilder.toMedicationDTO(prosumerOptional.get());
    }

    public UUID insert(MedicationDTO medicationDTO) {
        Medication medication = MedicationBuilder.toEntity(medicationDTO);
        medication = medicationRepository.save(medication);
        LOGGER.debug("medication with id {} was inserted in db", medication.getId());
        System.out.println("test");
        return medication.getId();
    }

//    public medicationDTO updatemedication(UUID id){
//        Optional<medication> prosumerOptional = medicationRepository.update(id);
//        return null;
//    }

    public void deleteById(UUID id) {
        medicationRepository.deleteById(id);
    }

    public UUID update(MedicationDTO medicationDTO){
        Medication medication = MedicationBuilder.toEntity(medicationDTO);
        medication.setId(medicationDTO.getId());
        Optional<Medication> prosumerOptional = medicationRepository.findById(medication.getId());
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("medication with id {} was not found in db", medication.getId());
            throw new ResourceNotFoundException(Medication.class.getSimpleName() + " with id: " + medication.getId());
        }

        medication = medicationRepository.save(medication);
        LOGGER.debug("medication with id {} was updated in db", medication.getId());
        return medication.getId();
    }
}
