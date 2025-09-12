package com.example.correctionapp.notifier;


import com.example.correctionapp.observer.Observer;

public class EmployeeNotifier implements Observer<String> {

    @Override
    public void update(String message) {
        System.out.println("Notification envoyée : " + message);
    }
}
