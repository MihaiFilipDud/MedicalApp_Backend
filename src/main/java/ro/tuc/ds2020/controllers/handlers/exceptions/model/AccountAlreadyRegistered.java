package ro.tuc.ds2020.controllers.handlers.exceptions.model;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class AccountAlreadyRegistered extends CustomException {
    private static final String MESSAGE = "Account already registered!";
    private static final HttpStatus httpStatus = HttpStatus.IM_USED;

    public AccountAlreadyRegistered(String resource) {
        super(MESSAGE,httpStatus, resource, new ArrayList<>());
    }
}
