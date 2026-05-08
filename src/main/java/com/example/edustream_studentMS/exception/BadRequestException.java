package com.example.edustream_studentMS.exception;

public class BadRequestException extends ApplicationException {
    public BadRequestException(String message) {
        super(message, 400);
    }
}
