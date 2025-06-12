package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.model.Book;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    public List<Book> getAllBooks() {
        return Arrays.asList(
                new Book("978-1234567891", "Effective Java", "Joshua Bloch"),
                new Book("978-1234567892", "Clean Code", "Robert C. Martin"),
                new Book("978-1234567893", "Design Patterns", "Erich Gamma")
        );
    }
}
