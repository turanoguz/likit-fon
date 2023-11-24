package com.kogus.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.kogus.controller.FundController;
import com.kogus.result.DataResult;
import com.kogus.result.ErrorDataResult;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;


@ControllerAdvice(assignableTypes = FundController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<DataResult<Map<Path,String>>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<Path, String> errors = new HashMap<Path, String>();
        
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.put(violation.getPropertyPath() , violation.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDataResult<Map<Path, String>>(errors,"Doğrulama hataları.."));
        
          
    }
    
    
    

}
