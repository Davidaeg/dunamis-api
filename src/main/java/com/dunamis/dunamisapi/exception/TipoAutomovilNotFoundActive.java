package com.dunamis.dunamisapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class TipoAutomovilNotFoundActive {

    @ResponseBody
    @ExceptionHandler(TipoAutomovilNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> exceptionHandler(TipoAutomovilNotFoundException exception){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMesage", exception.getMessage());
        return errorMap;
    }
}
