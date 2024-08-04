package com.dunamis.dunamisapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class PermisosRolNotFoundActive {

    @ResponseBody
    @ExceptionHandler(PermisosRolNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> exceptionHandler(PermisosRolNotFoundException exception){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMesage", exception.getMessage());
        return errorMap;
    }
}
