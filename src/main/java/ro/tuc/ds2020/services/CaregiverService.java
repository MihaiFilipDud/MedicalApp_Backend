package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.AccountAlreadyRegistered;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.builders.CaregiverBuilder;
import ro.tuc.ds2020.dtos.builders.PatientBuilder;
import ro.tuc.ds2020.entities.Account;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.repositories.AccountRepository;
import ro.tuc.ds2020.repositories.CaregiverRepository;
import ro.tuc.ds2020.repositories.CaregiverRepository;
import ro.tuc.ds2020.repositories.PatientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CaregiverService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaregiverService.class);
    private final CaregiverRepository caregiverRepository;
    private final AccountRepository accountRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public CaregiverService(CaregiverRepository caregiverRepository, AccountRepository accountRepository, PatientRepository patientRepository) {
        this.caregiverRepository = caregiverRepository;
        this.accountRepository = accountRepository;
        this.patientRepository = patientRepository;
    }

    public List<CaregiverDTO> findCaregivers() {
        List<Caregiver> caregiverList = caregiverRepository.findAll();
        return caregiverList.stream()
                .map(CaregiverBuilder::toCaregiverDTO)
                .collect(Collectors.toList());
    }

    public CaregiverDTO findCaregiverById(UUID id) {
        Optional<Caregiver> prosumerOptional = caregiverRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("caregiver with id {} was not found in db", id);
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with id: " + id);
        }
        return CaregiverBuilder.toCaregiverDTO(prosumerOptional.get());
    }

    public UUID insert(CaregiverDTO caregiverDTO) {
        Caregiver caregiver = CaregiverBuilder.toEntity(caregiverDTO);
        caregiver = caregiverRepository.save(caregiver);
        LOGGER.debug("caregiver with id {} was inserted in db", caregiver.getId());
        System.out.println("test");
        return caregiver.getId();
    }



    public void deleteById(UUID id) {
        caregiverRepository.deleteById(id);
    }


    public UUID update(CaregiverDTO caregiverDTO){
        Caregiver caregiver = CaregiverBuilder.toEntity(caregiverDTO);
        caregiver.setId(caregiverDTO.getId());
        Optional<Caregiver> prosumerOptional = caregiverRepository.findById(caregiver.getId());
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("caregiver with id {} was not found in db", caregiver.getId());
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with id: " + caregiver.getId());
        }

        caregiver = caregiverRepository.save(caregiver);
        LOGGER.debug("caregiver with id {} was updated in db", caregiver.getId());
        return caregiver.getId();
    }


    public UUID registerCaregiver(CaregiverDTO caregiverDTO){
        Caregiver caregiver = CaregiverBuilder.toEntity(caregiverDTO);
        System.out.println(caregiver.getAccount().getUsername());
        Optional<Account> testAcc = accountRepository.findById(caregiver.getAccount().getUsername());
        if (testAcc.isPresent()) {
            LOGGER.error("caregiver with username {} was  found in db", caregiver.getAccount().getUsername());
            throw new AccountAlreadyRegistered(Account.class.getSimpleName() + " with id: " + caregiver.getAccount().getUsername());
        }

        caregiver = caregiverRepository.save(caregiver);
        LOGGER.debug("Caregiver with id {} was inserted in db", caregiver.getId());

        Account account = caregiver.getAccount();

        account.setPerson(caregiver);

        account = accountRepository.save(account);
        LOGGER.debug("Account with id {} was inserted in db", account.getUsername());
        return caregiver.getId();
    }


    public List<PatientDTO> findPatients(UUID caregiverID) {
        List<Patient> patientList = patientRepository.findByCaregiver_Id(caregiverID);
        return patientList.stream()
                .map(PatientBuilder::toPatientDTO)
                .collect(Collectors.toList());
    }
}
