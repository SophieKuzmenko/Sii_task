package com.github.skuzmenko.Sii_task.exception;

import org.springframework.http.HttpStatus;

public class CustomIllegalArgException extends CustomRuntimeException{
    public CustomIllegalArgException(String message) {
        super(message);
        setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
        setErrorCode("INVALID_FIELD_VALUE");
    }
}
