package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("1", "The Great Gatsby", "F. Scott Fitzgerald"));
        books.add(new Book("2", "1984", "George Orwell"));
        books.add(new Book("3", "To Kill a Mockingbird", "Harper Lee"));
        return books;
    }
}
