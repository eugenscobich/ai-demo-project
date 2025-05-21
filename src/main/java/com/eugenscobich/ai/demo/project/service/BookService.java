package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.model.Book;
import org.springframework.stereotype.Service;

import com.eugenscobich.ai.demo.project.model.BookEntity;
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
        return bookRepository.findAll().stream()
            .map(entity -> new Book(entity.getId(), entity.getName(), "")) // Author not in BookEntity, left empty
            .collect(Collectors.toList());
    }
}