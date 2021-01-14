package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.services.PersonService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<List<PersonDTO>> getPersons() {
        List<PersonDTO> dtos = personService.findPersons();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody PersonDetailsDTO personDTO) {
        UUID personID = personService.insert(personDTO);
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<PersonDTO> getPerson(@PathVariable("id") UUID personId) {
        PersonDTO dto = personService.findPersonById(personId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<PersonDTO> deletePerson(@PathVariable("id") UUID personId) {
        personService.deleteById(personId);
        return new ResponseEntity<>(new PersonDTO(), HttpStatus.OK);
    }


    @PostMapping(value = "/update/{id}")
    @Secured({"ROLE_DOCTOR"})
    public ResponseEntity<UUID> updatePerson(@Valid @RequestBody PersonDetailsDTO personDTO) {
        UUID personID = personService.update(personDTO);
        return new ResponseEntity<>(personID, HttpStatus.I_AM_A_TEAPOT);
    }




    //TODO: UPDATE, DELETE per resource


}
