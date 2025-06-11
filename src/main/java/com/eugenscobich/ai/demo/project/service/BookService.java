package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.model.Book;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    public List<Book> getBooks() {
        return Arrays.asList(
            new Book(1, "Effective Java", "Joshua Bloch"),
            new Book(2, "Clean Code", "Robert C. Martin"),
            new Book(3, "Spring in Action", "Craig Walls")
        );
    }
}
