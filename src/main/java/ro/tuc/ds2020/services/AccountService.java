package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.AccountDTO;
import ro.tuc.ds2020.dtos.AccountDTO;
import ro.tuc.ds2020.dtos.builders.AccountBuilder;
import ro.tuc.ds2020.dtos.builders.AccountBuilder;
import ro.tuc.ds2020.entities.Account;
import ro.tuc.ds2020.entities.Account;
import ro.tuc.ds2020.repositories.AccountRepository;
import ro.tuc.ds2020.repositories.AccountRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountDTO> findAccounts() {
        List<Account> accountList = accountRepository.findAll();
        return accountList.stream()
                .map(AccountBuilder::toAccountDTO)
                .collect(Collectors.toList());
    }

    public AccountDTO findAccountById(String id) {
        Optional<Account> prosumerOptional = accountRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("account with id {} was not found in db", id);
            throw new ResourceNotFoundException(Account.class.getSimpleName() + " with id: " + id);
        }
        return AccountBuilder.toAccountDTO(prosumerOptional.get());
    }

    public String insert(AccountDTO accountDTO) {
        Account account = AccountBuilder.toEntity(accountDTO);
        account = accountRepository.save(account);
        LOGGER.debug("account with id {} was inserted in db", account.getUsername());
        System.out.println("test");
        return account.getUsername();
    }

//    public accountDTO updateaccount(UUID id){
//        Optional<account> prosumerOptional = accountRepository.update(id);
//        return null;
//    }

    public void deleteById(String id) {
        accountRepository.deleteById(id);
    }

    public String update(AccountDTO accountDTO){
        Account account = AccountBuilder.toEntity(accountDTO);
        account.setUsername(accountDTO.getUsername());
        Optional<Account> prosumerOptional = accountRepository.findById(account.getUsername());
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("account with id {} was not found in db", account.getUsername());
            throw new ResourceNotFoundException(Account.class.getSimpleName() + " with id: " + account.getUsername());
        }

        account = accountRepository.save(account);
        LOGGER.debug("account with id {} was updated in db", account.getUsername());
        return account.getUsername();
    }
}



