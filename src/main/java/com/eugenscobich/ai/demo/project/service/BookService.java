package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.model.Book;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    public List<Book> getBooks() {
        return Arrays.asList(
                new Book("Spring in Action", "Craig Walls"),
                new Book("Effective Java", "Joshua Bloch"),
                new Book("Clean Code", "Robert C. Martin")
        );
    }
}
