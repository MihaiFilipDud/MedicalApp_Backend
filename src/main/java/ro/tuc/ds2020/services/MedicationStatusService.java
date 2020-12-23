package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;

import ro.tuc.ds2020.entities.MedicationStatus;
import ro.tuc.ds2020.repositories.MedicationStatusRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicationStatusService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicationStatusService.class);
    private final MedicationStatusRepository medicationStatusRepository;

    @Autowired
    public MedicationStatusService(MedicationStatusRepository medicationStatusRepository) {
        this.medicationStatusRepository = medicationStatusRepository;
    }

    
}
