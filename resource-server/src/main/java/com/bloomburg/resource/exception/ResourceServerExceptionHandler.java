package com.bloomburg.resource.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResourceServerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { MalformedExpressionException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException runtimeException, WebRequest webRequest) {
        String message = "Computation Resource Server could not process request. Please check for invalid input!";
        return handleExceptionInternal(runtimeException, message, new HttpHeaders(), HttpStatus.CONFLICT, webRequest);
    }
}
