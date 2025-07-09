package com.ps.foodcampus.application.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message+" not found");
    }
}
