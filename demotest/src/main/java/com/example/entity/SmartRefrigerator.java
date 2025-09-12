package com.example.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class SmartRefrigerator {

    private List<FoodItem> items = new ArrayList<>();
    public boolean isEmpty() {
        return items.size() == 0;
    }

    public void addItem(String nameItem, LocalDateTime expirationDateTime) {
        items.add(new FoodItem(nameItem, expirationDateTime));
    }

    public List<FoodItem> getItems() {
        return items;
    }
}
