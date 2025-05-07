package com.github.skuzmenko.Sii_task.exception;

import org.springframework.http.HttpStatus;

public class AbsentRecordException extends CustomRuntimeException{
    public AbsentRecordException(String message) {
        super(message);
        setHttpStatus(HttpStatus.NOT_FOUND);
        setErrorCode("RECORD_NOT_PRESENT");
    }
}
