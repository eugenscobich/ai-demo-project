package com.eugenscobich.ai.demo.project.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Arrays;
import com.eugenscobich.ai.demo.project.model.Book;

@Service
public class BookService {
    public List<Book> getBooks() {
        return Arrays.asList(
            new Book(1L, "Effective Java", "Joshua Bloch"),
            new Book(2L, "Clean Code", "Robert C. Martin"),
            new Book(3L, "Spring in Action", "Craig Walls")
        );
    }
}
