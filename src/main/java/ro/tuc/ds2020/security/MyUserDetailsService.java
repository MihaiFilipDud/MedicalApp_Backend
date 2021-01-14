package ro.tuc.ds2020.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.repositories.PersonRepository;

import java.util.*;

@Service
public class MyUserDetailsService implements UserDetailsService, UserDetails {

    @Autowired
    PersonRepository personRepository;

    private Person person;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Person> person = personRepository.findByAccount_Username(s);

        if (!person.isPresent()){
            throw new UsernameNotFoundException("The username is inncorect");
        }

        this.person = person.get();
        return new User(this.person.getAccount().getUsername(), this.person.getAccount().getPassword(), getAuthorities());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        System.out.println(person.getClass().getSimpleName().toUpperCase(Locale.ROOT));
        authorities.add(new SimpleGrantedAuthority("ROLE_"+ person.getClass().getSimpleName().toUpperCase(Locale.ROOT)));
        return authorities;
    }

    @Override
    public String getPassword() {
        return person.getAccount().getPassword();
    }

    @Override
    public String getUsername() {
        return person.getAccount().getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}