package ro.tuc.ds2020.entities;


import javax.persistence.Entity;
import java.io.Serializable;

@Entity(name = "doctor")
public class Doctor extends Person implements Serializable {

    public Doctor() {
    }

    public Doctor(String name, String address, int age, String gender, Account account) {
        super(name, address, age, gender, account);
    }

    public Doctor(String name, String address, int age) {
        super(name, address, age);
    }


}
