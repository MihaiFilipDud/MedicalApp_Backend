package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.AccountDTO;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.dtos.builders.AccountBuilder;
import ro.tuc.ds2020.dtos.builders.PersonBuilder;
import ro.tuc.ds2020.entities.Account;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.repositories.AccountRepository;
import ro.tuc.ds2020.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository personRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, AccountRepository accountRepository) {
        this.personRepository = personRepository;
        this.accountRepository = accountRepository;
    }

    public List<PersonDTO> findPersons() {
        List<Person> personList = personRepository.findAll();
        return personList.stream()
                .map(PersonBuilder::toPersonDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findPersonById(UUID id) {
        Optional<Person> prosumerOptional = personRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", id);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + id);
        }
        return PersonBuilder.toPersonDTO(prosumerOptional.get());
    }

    public UUID insert(PersonDetailsDTO personDTO) {
        Person person = PersonBuilder.toEntity(personDTO);
        person = personRepository.save(person);
        LOGGER.debug("Person with id {} was inserted in db", person.getId());
        System.out.println("test");
        return person.getId();
    }

//    public PersonDTO updatePerson(UUID id){
//        Optional<Person> prosumerOptional = personRepository.update(id);
//        return null;
//    }

    public void deleteById(UUID id) {
        personRepository.deleteById(id);
    }

    public UUID update(PersonDetailsDTO personDTO){
        Person person = PersonBuilder.toEntity(personDTO);
        person.setId(personDTO.getId());
        Optional<Person> prosumerOptional = personRepository.findById(person.getId());
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", person.getId());
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + person.getId());
        }

        person = personRepository.save(person);
        LOGGER.debug("Person with id {} was updated in db", person.getId());
        return person.getId();
    }


    public UUID registerPerson(PersonDetailsDTO personDTO, AccountDTO accountDTO){
        Person person = PersonBuilder.toEntity(personDTO);
        person = personRepository.save(person);
        LOGGER.debug("Person with id {} was inserted in db", person.getId());
//        Account account = AccountBuilder.toEntity(accountDTO);
//        account.setPerson(person);
//        account = accountRepository.save(account);
//        LOGGER.debug("Account with id {} was inserted in db", account.getUsername());
        return person.getId();
    }



}
