package com.kogus.exceptions;

public class NoFoundNoArgumentConstructor extends RuntimeException {
    public NoFoundNoArgumentConstructor(String errorMessage) {
        super(errorMessage);
    }
}
