package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.repositories.PersonRepository;
import ro.tuc.ds2020.security.JwtUtil;
import ro.tuc.ds2020.security.MyUserDetailsService;
import ro.tuc.ds2020.services.AccountService;
import ro.tuc.ds2020.services.LoginService;

@RestController
@CrossOrigin
@RequestMapping(value = "/login")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

//    @PostMapping()
//    public ResponseEntity<LoginDetails> insertProsumer(@RequestBody AccountDTO accountDTO) {
//        LoginDetails person = loginService.login(accountDTO);
//        return new ResponseEntity<>(person, HttpStatus.ACCEPTED);
//    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e){
            //throw  new Exception("Incorrect username or password", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.UNAUTHORIZED);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        //final UserDetails user =  userDetailsService.loadUserByUsername(authRequest.getUsername());
        final Person person = personRepository.findByAccount_Username(authRequest.getUsername()).get();
        System.out.println(userDetails.getAuthorities());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(
                new LoginDetails(person.getId(), person.getClass().getSimpleName(), jwt)
        );

    }


}
