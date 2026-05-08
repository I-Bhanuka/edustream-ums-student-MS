package com.example.edustream_studentMS.exception;

public class CourseNotFoundException extends NotFoundException {
    public CourseNotFoundException(String courseId) {
        super("Course not found with ID: " + courseId);
    }
}
