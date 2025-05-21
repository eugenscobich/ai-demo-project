package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.eugenscobich.ai.demo.project.entity.BookEntity;
import com.eugenscobich.ai.demo.project.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll().stream()
                .map(entity -> new Book(entity.getId(), entity.getName(), entity.getIsbn()))
                .collect(Collectors.toList());
    }

    public Book getBookById(Long id) {
        BookEntity entity = bookRepository.findById(id).orElse(null);
        if (entity == null) return null;
        return new Book(entity.getId(), entity.getName(), entity.getIsbn());
    }

    public Book createBook(Book book) {
        BookEntity entity = new BookEntity();
        entity.setName(book.getTitle());
        entity.setIsbn(book.getAuthor());
        entity = bookRepository.save(entity);
        return new Book(entity.getId(), entity.getName(), entity.getIsbn());
    }

    public Book updateBook(Long id, Book book) {
        BookEntity entity = bookRepository.findById(id).orElse(null);
        if (entity == null) return null;
        entity.setName(book.getTitle());
        entity.setIsbn(book.getAuthor());
        entity = bookRepository.save(entity);
        return new Book(entity.getId(), entity.getName(), entity.getIsbn());
    }

    public boolean deleteBook(Long id) {
        if (!bookRepository.existsById(id)) return false;
        bookRepository.deleteById(id);
        return true;
    }

    }