package com.example.correctionapp.observer;

public interface Observer<T> {
    void update(T notification);
}