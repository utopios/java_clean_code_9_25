package com.kata.parcmeter.exception;

public class InvalidSessionException extends ParkingException {
    public InvalidSessionException(String message) {
        super("Invalid parking session: " + message);
    }
}
