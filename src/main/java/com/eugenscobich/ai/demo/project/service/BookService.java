package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.model.Book;
import org.springframework.stereotype.Service;

import com.eugenscobich.ai.demo.project.entity.BookEntity;
import com.eugenscobich.ai.demo.project.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        // Converts BookEntity to Book model if needed
        return bookRepository.findAll().stream()
            .map(entity -> new Book(
                    entity.getId() != null ? entity.getId().toString() : null,
                    entity.getName(),
                    entity.getIsbn()))
            .collect(Collectors.toList());
    }
}