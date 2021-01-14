package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.AccountDTO;
import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.PatientDetailsDTO;
import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.services.MedicationPlanService;
import ro.tuc.ds2020.services.MedicationService;
import ro.tuc.ds2020.services.PatientService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;

@RestController
@CrossOrigin
@RequestMapping(value = "/patient")
public class PatientController {

    private final PatientService patientService;
    private final MedicationPlanService medicationPlanService;

    @Autowired
    public PatientController(PatientService patientService, MedicationPlanService medicayionPlanService) {
        this.patientService = patientService;
        this.medicationPlanService = medicayionPlanService;
    }

    @GetMapping()
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<List<PatientDTO>> getPatients() {

        List<PatientDTO> dtos = patientService.findPatients();
//        for(PatientDTO patient:dtos){
//            for (MedicationPlan medPlan:patient.getMedPlans()){
//                medPlan.setPatient(new Patient("", "", 0, ""));
//            }
//        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }

    @PostMapping()
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<UUID> insertProsumer(@RequestBody PatientDTO patientDTO) {
        UUID patientID = patientService.insert(patientDTO);
        return new ResponseEntity<>(patientID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    @Secured({"ROLE_DOCTOR", "ROLE CAREGIVER", "ROLE_PATIENT"})
    public ResponseEntity<PatientDTO> getPatient(@PathVariable("id") UUID patientId) {
        PatientDTO dto = patientService.findPatientById(patientId);
        System.out.println(dto);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<PatientDTO> deletePatient(@PathVariable("id") UUID patientId) {
        patientService.deleteById(patientId);
        return new ResponseEntity<>(new PatientDTO(), HttpStatus.OK);
    }


    @PostMapping(value = "/update/{id}")
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<UUID> updatePatient(@RequestBody PatientDTO patientDTO) {
        UUID patientID = patientService.update(patientDTO);
        return new ResponseEntity<>(patientID, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<UUID> registerPatient(@RequestBody PatientDTO patientDTO){

        UUID patientID = patientService.registerPatient(patientDTO);
        return new ResponseEntity<>(patientID, HttpStatus.CREATED);
    }

    @PostMapping(value = "/addCaregiver")
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<UUID> addCaregiver(@RequestParam(value="c_id") UUID caregiverID, @RequestParam(value="p_id") UUID patientID){

        UUID id = patientService.addCaregiver(caregiverID, patientID);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PostMapping(value = "/getMedicationPlans")
    @Secured({"ROLE_DOCTOR", "ROLE_CAREGIVER", "ROLE_PATIENT"})
    public ResponseEntity<List<MedicationPlanDTO>>getPatients(@RequestBody UUID patientID) {
        List<MedicationPlanDTO> dtos = medicationPlanService.findMedicationPlansForPatient(patientID);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    //TODO: UPDATE, DELETE per resource


}