package com.filmindustry.candidatescreening.functionclasses;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomValidationMessage extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,HttpHeaders headers,
            HttpStatus status,WebRequest request) 
    {
    		Map<String,String> customErrors= new HashMap<>();
    		exception.getBindingResult().getAllErrors().forEach((errorMsg)->{
    			String data="error";
    			String customMsg=errorMsg.getDefaultMessage();
    			customErrors.put(data,customMsg);
    		});
    		return new ResponseEntity<Object>(customErrors,HttpStatus.OK);
    }
}
