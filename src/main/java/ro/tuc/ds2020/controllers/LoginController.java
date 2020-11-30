package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.AccountDTO;
import ro.tuc.ds2020.dtos.LoginDetails;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.services.AccountService;
import ro.tuc.ds2020.services.LoginService;

@RestController
@CrossOrigin
@RequestMapping(value = "/login")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping()
    public ResponseEntity<LoginDetails> insertProsumer(@RequestBody AccountDTO accountDTO) {
        LoginDetails person = loginService.login(accountDTO);
        return new ResponseEntity<>(person, HttpStatus.ACCEPTED);
    }


}
