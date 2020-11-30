package ro.tuc.ds2020.dtos;

import java.util.UUID;

public class LoginDetails {

    private UUID personId;

    private String role;

    public LoginDetails() {
    }

    public LoginDetails(UUID personId, String role) {
        this.personId = personId;
        this.role = role;
    }

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
