package com.faceit.irs.validation_xml.exception;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException{

    final int lineNumber;
    final int columnNumber;

    public ValidationException(String message, int lineNumber, int columnNumber) {
        super(message);
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }

    public ValidationException(String message, Throwable cause, int lineNumber, int columnNumber) {
        super(message, cause);
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }
}
