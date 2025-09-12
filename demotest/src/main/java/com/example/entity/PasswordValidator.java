package com.example.entity;

public class PasswordValidator {

    private static final int MINIMUM_LENGTH = 8;
    public boolean isValid(String password) {
        return hasMinimumLength(password);
    }

    private boolean hasMinimumLength(String password) {
        return password.length() >= MINIMUM_LENGTH;
    }
}
