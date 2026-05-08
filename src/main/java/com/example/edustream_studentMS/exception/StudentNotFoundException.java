package com.example.edustream_studentMS.exception;

public class StudentNotFoundException extends NotFoundException {
    public StudentNotFoundException(String info) {
        super("Student not found with, " + info);
    }
}
