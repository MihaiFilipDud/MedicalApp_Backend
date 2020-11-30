package ro.tuc.ds2020.entities;


import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity(name = "account")
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="username")
public class Account implements Serializable {


    @Id
    private String username = null;

    @Column
    private String password = null;

    @OneToOne(mappedBy = "account", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private Person person;


    public Account(){

    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(String username, String password, Person person) {
        this.username = username;
        this.password = password;
        this.person = person;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
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
