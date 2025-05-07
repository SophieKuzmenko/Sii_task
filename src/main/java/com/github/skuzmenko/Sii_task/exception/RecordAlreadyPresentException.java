package com.github.skuzmenko.Sii_task.exception;

import org.springframework.http.HttpStatus;

public class RecordAlreadyPresentException extends CustomRuntimeException {

    public RecordAlreadyPresentException(String message) {
        super(message);
        setHttpStatus(HttpStatus.CONFLICT);
        setErrorCode("RECORD_ALREADY_PRESENT");
    }
}
