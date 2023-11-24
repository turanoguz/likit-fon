package com.kogus.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoFoundBranchException extends RuntimeException {
    public NoFoundBranchException(String message) {
        super(message);
    }
}

