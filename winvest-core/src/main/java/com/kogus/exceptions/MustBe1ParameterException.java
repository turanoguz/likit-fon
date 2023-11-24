package com.kogus.exceptions;

public class MustBe1ParameterException extends RuntimeException {
    public MustBe1ParameterException(String errorMessage) {
        super(errorMessage);
    }

}
