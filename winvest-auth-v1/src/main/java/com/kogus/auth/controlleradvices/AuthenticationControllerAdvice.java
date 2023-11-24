package com.kogus.auth.controlleradvices;

import com.kogus.auth.controller.AuthenticationController;
import com.kogus.auth.exceptions.EmailInUseException;
import com.kogus.auth.exceptions.UsernameInUseException;
import com.kogus.auth.exceptions.UsernameNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(assignableTypes = {AuthenticationController.class})
public class AuthenticationControllerAdvice
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EmailInUseException.class, UsernameInUseException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {AuthenticationException.class, UsernameNotFoundException.class})
    protected ResponseEntity<Object> handleBadRequest(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, "Kullanıcı adı veya şifre yanlış",
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
