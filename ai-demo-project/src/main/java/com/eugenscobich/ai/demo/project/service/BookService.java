package com.eugenscobich.ai.demo.project.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Arrays;

@Service
public class BookService {
    public List<String> getBooks() {
        return Arrays.asList(
                "Spring in Action",
                "Clean Code",
                "Effective Java",
                "The Pragmatic Programmer"
        );
    }
}
