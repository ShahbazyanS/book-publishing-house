package com.example.book.publishinghouse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResourcesNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourcesNotFoundException e) {
//        ErrorDetails errorDetails = new ErrorDetails(e.getMessage(), ErrorCode.SERVER_ERROR);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateValueException.class)
    public ResponseEntity<?> handleDuplicateValue(DuplicateValueException e) {
//        ErrorDetails errorDetails = new ErrorDetails(e.getMessage(), ErrorCode.SERVER_ERROR);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
    }
}
