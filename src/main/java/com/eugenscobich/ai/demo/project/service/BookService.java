package com.eugenscobich.ai.demo.project.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Arrays;
import java.util.Map;

@Service
public class BookService {
    public List<Map<String, String>> getBooks() {
        return Arrays.asList(
            Map.of("title", "Clean Code", "author", "Robert C. Martin"),
            Map.of("title", "Effective Java", "author", "Joshua Bloch"),
            Map.of("title", "Spring in Action", "author", "Craig Walls")
        );
    }
}
