package ro.tuc.ds2020.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ro.tuc.ds2020.entities.Person;

import javax.validation.constraints.NotNull;

public class AccountDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @JsonIgnore
    private Person person;


    public AccountDTO() {
    }

    public AccountDTO(@NotNull String username, @NotNull String password) {
        this.username = username;
        this.password = password;
    }

    public AccountDTO(@NotNull String username, @NotNull String password, Person person) {
        this.username = username;
        this.password = password;
        this.person = person;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", person=" + person +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
