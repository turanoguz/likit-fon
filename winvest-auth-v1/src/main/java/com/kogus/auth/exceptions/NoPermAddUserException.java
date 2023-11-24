package com.kogus.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)

public class NoPermAddUserException extends RuntimeException {
    public NoPermAddUserException(String message) {
        super(message);
    }
}
