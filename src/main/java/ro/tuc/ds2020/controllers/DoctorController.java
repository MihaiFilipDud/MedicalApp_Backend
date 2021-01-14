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
import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.services.MedicationPlanService;
import ro.tuc.ds2020.services.MedicationService;
import ro.tuc.ds2020.services.DoctorService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;

@RestController
@CrossOrigin
@RequestMapping(value = "/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final MedicationPlanService medicationPlanService;

    @Autowired
    public DoctorController(DoctorService doctorService, MedicationPlanService medicayionPlanService) {
        this.doctorService = doctorService;
        this.medicationPlanService = medicayionPlanService;
    }

    @GetMapping()
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<List<DoctorDTO>> getDoctors() {

        List<DoctorDTO> dtos = doctorService.findDoctors();
//        for(DoctorDTO doctor:dtos){
//            for (MedicationPlan medPlan:doctor.getMedPlans()){
//                medPlan.setDoctor(new Doctor("", "", 0, ""));
//            }
//        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }

    @PostMapping()
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<UUID> insertProsumer(@RequestBody DoctorDTO doctorDTO) {
        UUID doctorID = doctorService.insert(doctorDTO);
        return new ResponseEntity<>(doctorID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<DoctorDTO> getDoctor(@PathVariable("id") UUID doctorId) {
        DoctorDTO dto = doctorService.findDoctorById(doctorId);
        System.out.println(dto);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<DoctorDTO> deleteDoctor(@PathVariable("id") UUID doctorId) {
        doctorService.deleteById(doctorId);
        return new ResponseEntity<>(new DoctorDTO(), HttpStatus.OK);
    }


    @PostMapping(value = "/update/{id}")
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<UUID> updateDoctor(@RequestBody DoctorDTO doctorDTO) {
        UUID doctorID = doctorService.update(doctorDTO);
        return new ResponseEntity<>(doctorID, HttpStatus.OK);
    }



    //TODO: UPDATE, DELETE per resource


}