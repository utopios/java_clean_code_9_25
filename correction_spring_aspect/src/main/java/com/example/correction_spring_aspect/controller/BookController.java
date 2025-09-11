package com.example.correction_spring_aspect.controller;

import com.example.correction_spring_aspect.dto.BookDTO;
import com.example.correction_spring_aspect.entity.Book;
import com.example.correction_spring_aspect.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody BookDTO bookDTO) {
        Book book = bookService.save(bookDTO.getName(), bookDTO.getAuthor());
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<List<Book>> get() {
        List<Book> books = bookService.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> get(@PathVariable long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }
}
