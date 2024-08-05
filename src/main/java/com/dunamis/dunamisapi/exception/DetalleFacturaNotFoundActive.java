package com.dunamis.dunamisapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.SplittableRandom;

@ControllerAdvice
public class DetalleFacturaNotFoundActive {

    @ResponseBody
    @ExceptionHandler(DetalleFacturaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> exceptionHandler(DetalleFacturaNotFoundException exception){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMesage", exception.getMessage());
        return errorMap;
    }
}
