package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.model.Book;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    public List<Book> getAllBooks() {
        return Arrays.asList(
            new Book("1984", "George Orwell"),
            new Book("To Kill a Mockingbird", "Harper Lee"),
            new Book("The Great Gatsby", "F. Scott Fitzgerald")
        );
    }
}
