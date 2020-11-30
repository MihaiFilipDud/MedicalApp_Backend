package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.AccountDTO;
import ro.tuc.ds2020.services.AccountService;

import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin
@RequestMapping(value = "/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping()
    public ResponseEntity<List<AccountDTO>> getAccounts() {
        List<AccountDTO> dtos = accountService.findAccounts();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> insertProsumer(@RequestBody AccountDTO accountDTO) {
        String accountID = accountService.insert(accountDTO);
        return new ResponseEntity<>(accountID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable("id") String accountId) {
        AccountDTO dto = accountService.findAccountById(accountId);
        System.out.println(dto.toString());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<AccountDTO> deleteAccount(@PathVariable("id") String accountId) {
        accountService.deleteById(accountId);
        return new ResponseEntity<>(new AccountDTO(), HttpStatus.OK);
    }


    @PostMapping(value = "/update/{id}")
    public ResponseEntity<String> updateAccount(@RequestBody AccountDTO accountDTO) {
        String accountID = accountService.update(accountDTO);
        return new ResponseEntity<>(accountID, HttpStatus.I_AM_A_TEAPOT);
    }



}
