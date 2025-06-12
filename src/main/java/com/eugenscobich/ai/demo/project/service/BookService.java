package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.model.Book;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    public List<Book> getBooks() {
        return Arrays.asList(
            new Book("1", "Book 1", "Author 1", 9.99),
            new Book("2", "Book 2", "Author 2", 12.99),
            new Book("3", "Book 3", "Author 3", 15.99)
        );
    }
}
