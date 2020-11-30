package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.AccountAlreadyRegistered;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.AccountDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.PatientDetailsDTO;
import ro.tuc.ds2020.dtos.builders.AccountBuilder;
import ro.tuc.ds2020.dtos.builders.PatientBuilder;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.builders.PatientBuilder;
import ro.tuc.ds2020.dtos.builders.PatientBuilder;
import ro.tuc.ds2020.entities.Account;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.repositories.AccountRepository;
import ro.tuc.ds2020.repositories.CaregiverRepository;
import ro.tuc.ds2020.repositories.PatientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);
    private final PatientRepository patientRepository;
    private final AccountRepository accountRepository;
    private final CaregiverRepository caregiverRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, AccountRepository accountRepository, CaregiverRepository caregiverRepository) {
        this.patientRepository = patientRepository;
        this.accountRepository = accountRepository;
        this.caregiverRepository = caregiverRepository;
    }

    public List<PatientDTO> findPatients() {
        List<Patient> patientList = patientRepository.findAll();
        return patientList.stream()
                .map(PatientBuilder::toPatientDTO)
                .collect(Collectors.toList());
    }

    public PatientDTO findPatientById(UUID id) {
        Optional<Patient> prosumerOptional = patientRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("patient with id {} was not found in db", id);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + id);
        }
        return PatientBuilder.toPatientDTO(prosumerOptional.get());
    }

    public Patient findPatientByIdFull(UUID id){
        Optional<Patient> prosumerOptional = patientRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("patient with id {} was not found in db", id);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + id);
        }
        return prosumerOptional.get();
    }

    public UUID insert(PatientDTO patientDTO) {
        Patient patient = PatientBuilder.toEntity(patientDTO);
        patient = patientRepository.save(patient);
        LOGGER.debug("patient with id {} was inserted in db", patient.getId());
        System.out.println("test");
        return patient.getId();
    }


    public void deleteById(UUID id) {
        patientRepository.deleteById(id);
    }

    public UUID update(PatientDTO patientDTO){
        Patient patient = PatientBuilder.toEntity(patientDTO);
        patient.setId(patientDTO.getId());
        Optional<Patient> prosumerOptional = patientRepository.findById(patient.getId());
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("patient with id {} was not found in db", patient.getId());
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + patient.getId());
        }

        patient = patientRepository.save(patient);
        LOGGER.debug("patient with id {} was updated in db", patient.getId());
        return patient.getId();
    }

    public UUID registerPatient(PatientDTO patientDTO){
        Patient patient = PatientBuilder.toEntity(patientDTO);
        System.out.println(patient.getAccount().getUsername());
        Optional<Account> testAcc = accountRepository.findById(patient.getAccount().getUsername());
        if (testAcc.isPresent()) {
            LOGGER.error("patient with username {} was  found in db", patient.getAccount().getUsername());
            throw new AccountAlreadyRegistered(Account.class.getSimpleName() + " with id: " + patient.getAccount().getUsername());
        }

        patient = patientRepository.save(patient);
        LOGGER.debug("Patient with id {} was inserted in db", patient.getId());

        Account account = patient.getAccount();

        account.setPerson(patient);

        account = accountRepository.save(account);
        LOGGER.debug("Account with id {} was inserted in db", account.getUsername());
        return patient.getId();
    }

    public UUID addCaregiver(UUID caregiverID, UUID patientID){
        Optional<Caregiver> caregiver = caregiverRepository.findById(caregiverID);
        if (!caregiver.isPresent()) {
            LOGGER.error("Caregiver with id {} was not found in db",caregiverID);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + caregiverID);
        }

        Optional<Patient> patient = patientRepository.findById(patientID);
        if (!patient.isPresent()) {
            LOGGER.error("patient with id {} was not found in db", patientID);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + patientID);
        }

        Patient p = patient.get();
        p.setCaregiver(caregiver.get());
        patientRepository.save(p);

        return patientID;
    }

}
