package com.example.charity.exception;

import com.example.charity.dto.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@ControllerAdvice
//@ResponseBody
@RestControllerAdvice
public class MyExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDto handleNotFound(Exception exception) {
        BaseException baseException = (BaseException) exception;

        logger.warn("Not found exception", exception);
        return new ErrorDto(baseException.getErrorCode(), baseException.getMessage(), HttpStatus.NOT_FOUND.value());
    }


}
