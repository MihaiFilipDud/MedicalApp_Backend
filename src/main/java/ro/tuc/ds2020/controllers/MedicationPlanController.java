package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.services.MedicationPlanService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/medicationPlan")
public class MedicationPlanController {

    private final MedicationPlanService medicationPlanService;

    @Autowired
    public MedicationPlanController(MedicationPlanService medicationPlanService) {
        this.medicationPlanService = medicationPlanService;
    }

    @GetMapping()
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<List<MedicationPlanDTO>> getMedicationPlans() {
        List<MedicationPlanDTO> dtos = medicationPlanService.findMedicationPlans();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<?> insertProsumer(@Valid @RequestBody MedicationPlanDTO medicationPlanDTO) {
        UUID medicationPlanID = medicationPlanService.insert(medicationPlanDTO);
        return new ResponseEntity<>(medicationPlanID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    @Secured({"ROLE_DOCTOR", "ROLE_CAREGIVER", "ROLE_PATIENT"})
    public ResponseEntity<MedicationPlanDTO> getMedicationPlan(@PathVariable("id") UUID medicationPlanId) {
        MedicationPlanDTO dto = medicationPlanService.findMedicationPlanById(medicationPlanId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<MedicationPlanDTO> deleteMedicationPlan(@PathVariable("id") UUID medicationPlanId) {
        medicationPlanService.deleteById(medicationPlanId);
        return new ResponseEntity<>(new MedicationPlanDTO(), HttpStatus.OK);
    }


    @PostMapping(value = "/update/{id}")
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<UUID> updateMedicationPlan(@Valid @RequestBody MedicationPlanDTO medicationPlanDTO) {
        UUID medicationPlanID = medicationPlanService.update(medicationPlanDTO);
        return new ResponseEntity<>(medicationPlanID, HttpStatus.I_AM_A_TEAPOT);
    }

}
