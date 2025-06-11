package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Arrays;

@Service
public class BookService {
    public List<Book> getAllBooks() {
        return Arrays.asList(
            new Book("1", "Clean Code", "Robert C. Martin"),
            new Book("2", "Effective Java", "Joshua Bloch"),
            new Book("3", "Spring in Action", "Craig Walls")
        );
    }
}
