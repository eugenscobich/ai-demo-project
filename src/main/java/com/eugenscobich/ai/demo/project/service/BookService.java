package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.model.Book;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    public List<Book> getBooks() {
        return Arrays.asList(
                new Book("1", "Spring Boot in Action", "Craig Walls"),
                new Book("2", "Effective Java", "Joshua Bloch"),
                new Book("3", "Clean Code", "Robert C. Martin")
        );
    }
}
