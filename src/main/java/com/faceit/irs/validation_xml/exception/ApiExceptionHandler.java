package com.faceit.irs.validation_xml.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {FileNotFoundException.class})
    public ResponseEntity<Object> handlerFileNotFoundException(FileNotFoundException e) {
        final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        final Map<String, String> message = Map.of(e.getMessage(), e.getMessage());

        final ApiException apiException = new ApiException(
                e.getClass().getSimpleName(),
                message,
                httpStatus,
                LocalDateTime.now());
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {RequestNoContentException.class})
    public ResponseEntity<Object> handlerRequestNoContentException(RequestNoContentException e) {
        final HttpStatus httpStatus = HttpStatus.NO_CONTENT;

        final Map<String, String> message = Map.of(e.getMessage(), e.getMessage());

        final ApiException apiException = new ApiException(
                e.getClass().getSimpleName(),
                message,
                httpStatus,
                LocalDateTime.now());
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {RequestUnsuportMediaTypeException.class})
    public ResponseEntity<Object> handlerRequestUnsuportMediaTypeException(RequestUnsuportMediaTypeException e) {
        final HttpStatus httpStatus = HttpStatus.UNSUPPORTED_MEDIA_TYPE;

        final Map<String, String> message = Map.of(e.getMessage(), e.getMessage());

        final ApiException apiException = new ApiException(
                e.getClass().getSimpleName(),
                message,
                httpStatus,
                LocalDateTime.now());
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<Object> handlerValidateException(ValidationException e) {
        final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        final Map<String, String> message = new HashMap<>();
        message.put("lineNumber", String.valueOf(e.getLineNumber()));
        message.put("columnNumber", String.valueOf(e.getColumnNumber()));
        message.put("description", e.getMessage());

        final ApiException apiException = new ApiException(
                e.getClass().getSimpleName(),
                message,
                httpStatus,
                LocalDateTime.now());
        return new ResponseEntity<>(apiException, httpStatus);
    }
}
