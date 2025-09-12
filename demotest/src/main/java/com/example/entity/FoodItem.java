package com.example.entity;

import java.time.LocalDateTime;


public class FoodItem {
    private String nameItem;
    private LocalDateTime expirationDateTime;

    public FoodItem(String nameItem, LocalDateTime expirationDateTime) {
        this.nameItem = nameItem;
        this.expirationDateTime = expirationDateTime;
    }

    public String getNameItem() {
        return nameItem;
    }

    public LocalDateTime getexpirationDateTime() {
        return expirationDateTime;
    }
}
