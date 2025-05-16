package com.eugenscobich.ai.demo.project.service;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    public List<String> getBooks() {
        return Arrays.asList("Harry Potter", "The Hobbit", "1984");
    }
}