package com.example.correctionapp.entity;

public enum TaskStatus {
    TODO("À faire"),
    IN_PROGRESS("En cours"),
    DONE("Terminée");

    private final String displayName;

    TaskStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean canTransitionTo(TaskStatus newStatus) {
        switch (this) {
            case TODO:
                return newStatus == IN_PROGRESS;
            case IN_PROGRESS:
                return newStatus == DONE || newStatus == TODO;
            case DONE:
                return false;
            default:
                return false;
        }
    }
}