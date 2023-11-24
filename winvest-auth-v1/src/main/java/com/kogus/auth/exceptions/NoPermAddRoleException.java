package com.kogus.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)

public class NoPermAddRoleException extends RuntimeException {
    public NoPermAddRoleException(String message) {
        super(message);
    }
}