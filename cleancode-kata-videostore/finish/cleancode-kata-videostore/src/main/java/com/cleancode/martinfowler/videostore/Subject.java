package com.cleancode.martinfowler.videostore;

public interface Subject<T> {
    void notify(T notification);
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
}
