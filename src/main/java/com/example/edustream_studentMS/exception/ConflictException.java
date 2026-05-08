package com.example.edustream_studentMS.exception;

public class ConflictException extends ApplicationException {
    public ConflictException(String message) {
        super(message, 409);
    }
}
