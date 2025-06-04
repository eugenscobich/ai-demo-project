package com.eugenscobich.ai.demo.project.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Arrays;
import com.eugenscobich.ai.demo.project.model.Book;

@Service
public class BookService {
    public List<Book> getAllBooks() {
        return Arrays.asList(
            new Book("The Hobbit", "J.R.R. Tolkien"),
            new Book("1984", "George Orwell"),
            new Book("To Kill a Mockingbird", "Harper Lee")
        );
    }
}
