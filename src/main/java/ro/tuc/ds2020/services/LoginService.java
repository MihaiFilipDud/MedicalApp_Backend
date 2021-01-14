package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.AccountDTO;
import ro.tuc.ds2020.dtos.LoginDetails;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.builders.PersonBuilder;
import ro.tuc.ds2020.entities.Account;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.repositories.AccountRepository;
import ro.tuc.ds2020.repositories.PersonRepository;

import java.util.Optional;

@Service
public class LoginService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository personRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public LoginService(PersonRepository personRepository, AccountRepository accountRepository) {
        this.personRepository = personRepository;
        this.accountRepository = accountRepository;
    }

    public LoginDetails login(AccountDTO accountDTO) {
        Optional<Person> person = personRepository.findByAccount_Username(accountDTO.getUsername());
        if (!person.isPresent()) {
            LOGGER.error("account with username {} was not found in db", accountDTO.getUsername());
            throw new ResourceNotFoundException(Account.class.getSimpleName() + " with id: " + accountDTO.getUsername());
        }
        PersonDTO p = PersonBuilder.toPersonDTO(person.get());
        System.out.println(person.get().getClass().getSimpleName());
        return null;
        //return new LoginDetails(p.getId(), person.get().getClass().getSimpleName());
    }
}
