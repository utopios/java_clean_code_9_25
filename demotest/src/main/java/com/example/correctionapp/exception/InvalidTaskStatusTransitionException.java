package com.example.correctionapp.exception;

public class InvalidTaskStatusTransitionException extends RuntimeException {
    public InvalidTaskStatusTransitionException(String format) {
        super(format);
    }
}
