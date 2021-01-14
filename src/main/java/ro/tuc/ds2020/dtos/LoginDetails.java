package ro.tuc.ds2020.dtos;

import java.util.UUID;

public class LoginDetails {

    private UUID personId;

    private String role;

    private String jwt;

    public LoginDetails() {
    }

    public LoginDetails(UUID personId, String role, String jwt) {
        this.personId = personId;
        this.role = role;
        this.jwt = jwt;
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

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
