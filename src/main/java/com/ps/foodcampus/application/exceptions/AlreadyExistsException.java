package com.ps.foodcampus.application.exceptions;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message + " already exists.");
    }
}
