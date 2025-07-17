package com.ps.foodcampus.application.exceptions;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message+ " is forbidden.");
    }
}
