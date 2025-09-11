package com.example.correction_spring_aspect.service;

import com.example.correction_spring_aspect.annotation.Log;
import com.example.correction_spring_aspect.annotation.Performance;
import com.example.correction_spring_aspect.entity.Book;
import com.example.correction_spring_aspect.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Performance
    public Book save(String name, String author) {
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    @Performance
    @Log
    public Book findById(long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()){
            return optionalBook.get();
        }
        throw new RuntimeException("Not found");
    }

    @Log
    public List<Book> findAll() {
        List<Book> books = (List<Book>) bookRepository.findAll();
        return books;
    }
}
