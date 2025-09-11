package com.kata.parcmeter.exception;

public class ParkingException extends RuntimeException {
    public ParkingException(String message) {
        super(message);
    }
    public ParkingException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
