package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Arrays;

@Service
public class BookService {
    public List<Book> getBooks() {
        return Arrays.asList(
                new Book("Effective Java", "Joshua Bloch", "978-0134685991"),
                new Book("Clean Code", "Robert C. Martin", "978-0132350884"),
                new Book("Design Patterns", "Erich Gamma", "978-0201633610")
        );
    }
}
