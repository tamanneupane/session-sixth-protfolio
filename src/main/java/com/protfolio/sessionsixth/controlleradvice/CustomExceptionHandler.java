package com.protfolio.sessionsixth.controlleradvice;

import com.protfolio.sessionsixth.exceptions.ServiceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ServiceNotFoundException.class)
    public ResponseEntity<Object> handleServiceNotFoundException(ServiceNotFoundException serviceNotFoundException){
        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("status", "error");
        errorBody.put("message", serviceNotFoundException.getMessage());
        return ResponseEntity.status(404).body(errorBody);
    }
}
