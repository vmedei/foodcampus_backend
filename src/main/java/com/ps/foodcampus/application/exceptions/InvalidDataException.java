package com.ps.foodcampus.application.exceptions;

public class InvalidDataException extends RuntimeException {
  public InvalidDataException(String message) {
    super(message + " is invalid.");
  }
}
