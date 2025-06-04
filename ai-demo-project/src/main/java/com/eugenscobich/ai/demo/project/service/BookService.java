package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.model.Book;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    public List<Book> getAllBooks() {
        return Arrays.asList(
            new Book("1", "The Hobbit", "J.R.R. Tolkien", 1937),
            new Book("2", "1984", "George Orwell", 1949),
            new Book("3", "To Kill a Mockingbird", "Harper Lee", 1960)
        );
    }
}