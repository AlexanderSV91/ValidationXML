package com.faceit.irs.validation_xml.exception;

public class RequestNoContentException extends RuntimeException {

    public RequestNoContentException(String message) {
        super(message);
    }

    public RequestNoContentException(String message, Throwable cause) {
        super(message, cause);
    }
}
