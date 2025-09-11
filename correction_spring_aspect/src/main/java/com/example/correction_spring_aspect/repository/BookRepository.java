package com.example.correction_spring_aspect.repository;

import com.example.correction_spring_aspect.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
