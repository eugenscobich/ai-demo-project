package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.model.Book;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    public List<Book> getAllBooks() {
        return Arrays.asList(
                new Book("Clean Code", "Robert C. Martin", "9780132350884"),
                new Book("Effective Java", "Joshua Bloch", "9780134685991"),
                new Book("Spring in Action", "Craig Walls", "9781617294945")
        );
    }
}
