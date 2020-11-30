package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.AccountDTO;
import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.dtos.builders.AccountBuilder;
import ro.tuc.ds2020.dtos.builders.DoctorBuilder;
import ro.tuc.ds2020.entities.Account;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.repositories.AccountRepository;
import ro.tuc.ds2020.repositories.DoctorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorService.class);
    private final DoctorRepository doctorRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, AccountRepository accountRepository) {
        this.doctorRepository = doctorRepository;
        this.accountRepository = accountRepository;
    }

    public List<DoctorDTO> findDoctors() {
        List<Doctor> doctorList = doctorRepository.findAll();
        return doctorList.stream()
                .map(DoctorBuilder::toDoctorDTO)
                .collect(Collectors.toList());
    }

    public DoctorDTO findDoctorById(UUID id) {
        Optional<Doctor> prosumerOptional = doctorRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Doctor with id {} was not found in db", id);
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + " with id: " + id);
        }
        return DoctorBuilder.toDoctorDTO(prosumerOptional.get());
    }

    public UUID insert(DoctorDTO doctorDTO) {
        Doctor doctor = DoctorBuilder.toEntity(doctorDTO);
        doctor = doctorRepository.save(doctor);
        LOGGER.debug("Doctor with id {} was inserted in db", doctor.getId());
        System.out.println("test");
        return doctor.getId();
    }

//    public DoctorDTO updateDoctor(UUID id){
//        Optional<Doctor> prosumerOptional = doctorRepository.update(id);
//        return null;
//    }

    public void deleteById(UUID id) {
        doctorRepository.deleteById(id);
    }

    public UUID update(DoctorDTO doctorDTO){
        Doctor doctor = DoctorBuilder.toEntity(doctorDTO);
        doctor.setId(doctorDTO.getId());
        Optional<Doctor> prosumerOptional = doctorRepository.findById(doctor.getId());
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Doctor with id {} was not found in db", doctor.getId());
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + " with id: " + doctor.getId());
        }

        doctor = doctorRepository.save(doctor);
        LOGGER.debug("Doctor with id {} was updated in db", doctor.getId());
        return doctor.getId();
    }


    public UUID registerDoctor(DoctorDTO doctorDTO, AccountDTO accountDTO){
        Doctor doctor = DoctorBuilder.toEntity(doctorDTO);
        doctor = doctorRepository.save(doctor);
        LOGGER.debug("Doctor with id {} was inserted in db", doctor.getId());
//        Account account = AccountBuilder.toEntity(accountDTO);
//        account.setDoctor(doctor);
//        account = accountRepository.save(account);
//        LOGGER.debug("Account with id {} was inserted in db", account.getUsername());
        return doctor.getId();
    }



}
