package org.example.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class SmartRefrigerator {

    private int itemCount = 0;
    private List<FoodItem> items = new ArrayList<>();
    public boolean isEmpty() {
        return itemCount ==0;
    }

    public void addItem(String nameItem, LocalDateTime expirationDateTime) {
        itemCount++;
        items.add(new FoodItem(nameItem, expirationDateTime));
    }

    public List<FoodItem> getItems() {
        return items;
    }
}
