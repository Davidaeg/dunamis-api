package com.dunamis.dunamisapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ReservacionNotFoundActive {

    @ResponseBody
    @ExceptionHandler(ReservacionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> exceptionHandler(ReservacionNotFoundException exception){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMesage", exception.getMessage());
        return errorMap;
    }
}
