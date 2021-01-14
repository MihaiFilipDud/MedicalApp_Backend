package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.services.CaregiverService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/caregiver")
public class CaregiverController {

    private final CaregiverService caregiverService;

    @Autowired
    public CaregiverController(CaregiverService caregiverService) {
        this.caregiverService = caregiverService;
    }

    @GetMapping()
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<List<CaregiverDTO>> getCaregivers() {
        List<CaregiverDTO> dtos = caregiverService.findCaregivers();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody CaregiverDTO caregiverDTO) {
        UUID caregiverID = caregiverService.insert(caregiverDTO);
        return new ResponseEntity<>(caregiverID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    @Secured({"ROLE_DOCTOR", "ROLE_CAREGIVER"})
    public ResponseEntity<CaregiverDTO> getCaregiver(@PathVariable("id") UUID caregiverId) {
        CaregiverDTO dto = caregiverService.findCaregiverById(caregiverId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<CaregiverDTO> deleteCaregiver(@PathVariable("id") UUID caregiverId) {
        caregiverService.deleteById(caregiverId);
        return new ResponseEntity<>(new CaregiverDTO(), HttpStatus.OK);
    }


    @PostMapping(value = "/update/{id}")
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<UUID> updateCaregiver(@Valid @RequestBody CaregiverDTO caregiverDTO) {
        UUID caregiverID = caregiverService.update(caregiverDTO);
        return new ResponseEntity<>(caregiverID, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<UUID> registerCaregiver(@RequestBody CaregiverDTO caregiverDTO) {

        UUID caregiverID = caregiverService.registerCaregiver(caregiverDTO);
        return new ResponseEntity<>(caregiverID, HttpStatus.CREATED);
    }

    @PostMapping(value = "/getPatients")
    @Secured({"ROLE_DOCTOR", "ROLE_CAREGIVER"})
    public ResponseEntity<List<PatientDTO>>getPatients(@RequestBody UUID caregiverID) {
        List<PatientDTO> dtos = caregiverService.findPatients(caregiverID);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


    //TODO: UPDATE, DELETE per resource


}

