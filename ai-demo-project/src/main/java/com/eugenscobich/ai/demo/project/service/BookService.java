package com.eugenscobich.ai.demo.project.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Arrays;

@Service
public class BookService {
    public List<String> getAllBooks() {
        return Arrays.asList("Book 1", "Book 2", "Book 3", "Book 4");
    }
}
