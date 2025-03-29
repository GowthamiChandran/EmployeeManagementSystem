package com.practice.crud.exception;

import com.practice.crud.dto.EmployeeDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EmployeeAllReadyExistsException extends RuntimeException {
    public EmployeeAllReadyExistsException(String message){
        super(message);
    }
}
