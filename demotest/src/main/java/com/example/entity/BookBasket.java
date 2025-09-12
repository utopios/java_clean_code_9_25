package com.example.entity;
import java.util.ArrayList;
import java.util.List;
public class BookBasket {

    private final List<Integer> books;

    public BookBasket() {
        books = new ArrayList<>();
    }

    public void addBook(Integer tome) {
        books.add(tome);
    }

    public List<Integer> getBooks() {
        return books;
    }
}
