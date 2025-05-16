package com.eugenscobich.ai.demo.project.service;

import org.springframework.stereotype.Service;
import com.eugenscobich.ai.demo.project.repository.BookRepository;
import java.util.List;
import com.eugenscobich.ai.demo.project.entity.BookEntity;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookEntity> getBooks() {
        return bookRepository.findAll();
    }
}