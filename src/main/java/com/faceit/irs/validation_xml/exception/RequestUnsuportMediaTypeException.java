package com.faceit.irs.validation_xml.exception;

public class RequestUnsuportMediaTypeException extends RuntimeException{

    public RequestUnsuportMediaTypeException(String message) {
        super(message);
    }

    public RequestUnsuportMediaTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
