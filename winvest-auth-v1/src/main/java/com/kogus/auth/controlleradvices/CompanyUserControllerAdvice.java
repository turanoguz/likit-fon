package com.kogus.auth.controlleradvices;

import com.kogus.auth.controller.CompanyAdminController;
import com.kogus.auth.exceptions.NoFoundBranchException;
import com.kogus.auth.exceptions.NoPermAddRoleException;
import com.kogus.auth.exceptions.NoPermAddUserException;
import com.kogus.auth.exceptions.UsernameNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(assignableTypes = {CompanyAdminController.class})
public class CompanyUserControllerAdvice
        extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {UsernameNotFoundException.class, NoFoundBranchException.class})
    protected ResponseEntity<Object> handleBadRequest(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {NoPermAddUserException.class, NoPermAddRoleException.class})
    protected ResponseEntity<Object> handleUnAuthorize(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }
}
