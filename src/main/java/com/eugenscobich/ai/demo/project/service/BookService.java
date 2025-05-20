package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.model.Book;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {

    public List<Book> getBooks() {
        return Arrays.asList(
                new Book(1L, "The Great Gatsby", "F. Scott Fitzgerald"),
                new Book(2L, "1984", "George Orwell"),
                new Book(3L, "To Kill a Mockingbird", "Harper Lee")
        );
    }
}
