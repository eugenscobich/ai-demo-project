package com.eugenscobich.ai.demo.project.controller;

import com.eugenscobich.ai.demo.project.model.Book;
import com.eugenscobich.ai.demo.project.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    
    @GetMapping
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }
}
