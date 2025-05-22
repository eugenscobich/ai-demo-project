package com.eugenscobich.ai.demo.project;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    public List<Book> listBooks() {
        return List.of(
                new Book(1L, "Clean Code", "Robert C. Martin"),
                new Book(2L, "Effective Java", "Joshua Bloch"),
                new Book(3L, "Spring in Action", "Craig Walls")
        );
    }
}
