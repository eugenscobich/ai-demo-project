package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.model.Book;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    public List<Book> getBooks() {
        return Arrays.asList(
            new Book("978-0140449136", "The Odyssey", "Homer"),
            new Book("978-0679783272", "War and Peace", "Leo Tolstoy"),
            new Book("978-0743273565", "The Great Gatsby", "F. Scott Fitzgerald")
        );
    }
}
