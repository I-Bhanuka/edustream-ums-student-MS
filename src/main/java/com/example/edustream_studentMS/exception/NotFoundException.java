package com.example.edustream_studentMS.exception;

public class NotFoundException extends ApplicationException {
    public NotFoundException(String message) {
        super(message, 404);
    }
}
