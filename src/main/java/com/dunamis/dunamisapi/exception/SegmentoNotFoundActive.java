package com.dunamis.dunamisapi.exception;

import com.dunamis.dunamisapi.model.Segmento;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class SegmentoNotFoundActive {

    @ResponseBody
    @ExceptionHandler(SegmentoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> exceptionHandler(SegmentoNotFoundException exception){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMesage", exception.getMessage());
        return errorMap;
    }
}
