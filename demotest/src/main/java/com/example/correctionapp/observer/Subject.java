package com.example.correctionapp.observer;

public interface Subject<T> {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(T notification);
}
