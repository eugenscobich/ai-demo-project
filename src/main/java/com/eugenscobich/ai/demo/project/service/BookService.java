package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import com.eugenscobich.ai.demo.project.repository.BookRepository;
import com.eugenscobich.ai.demo.project.model.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(entity -> new Book(entity.getName(), entity.getIsbn()))
                .collect(Collectors.toList());
    }

    // --- CRUD for BookEntity ---

    public Optional<BookEntity> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public BookEntity createBook(BookEntity book) {
        return bookRepository.save(book);
    }

    public Optional<BookEntity> updateBook(Long id, BookEntity newBook) {
        return bookRepository.findById(id).map(existing -> {
            existing.setName(newBook.getName());
            existing.setIsbn(newBook.getIsbn());
            return bookRepository.save(existing);
        });
    }

    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}