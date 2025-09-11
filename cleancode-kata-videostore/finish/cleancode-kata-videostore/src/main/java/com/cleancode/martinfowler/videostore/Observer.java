package com.cleancode.martinfowler.videostore;

public interface Observer<T> {
    void getNotification(T notification);
}
