package com.example.demo.Exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExeptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BuciExeption.class)
    public List<String> handleValidationException(
            BuciExeption validationException
    ) {
        log.debug("Validációs ikszepsön ", validationException);
        return List.of(validationException.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public List<String> handleEverything (
            Throwable throwable) {
        log.error("Valami elromlott URAM ", throwable);
        return List.of(throwable.getMessage());
    }
}
