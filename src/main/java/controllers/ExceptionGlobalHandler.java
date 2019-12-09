package controllers;

import exceptions.InvalidAccountException;
import exceptions.InvalidContractException;
import exceptions.InvalidCountryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler(InvalidCountryException.class)
    public ResponseEntity<Object> InvalidCountryExceptionHandler(InvalidCountryException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidContractException.class)
    public ResponseEntity<Object> InvalidContractExceptionHandler(InvalidContractException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidAccountException.class)
    public ResponseEntity<Object> InvalidAccountExceptionHandler(InvalidAccountException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
