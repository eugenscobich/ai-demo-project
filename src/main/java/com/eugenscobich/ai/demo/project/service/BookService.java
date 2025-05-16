package com.eugenscobich.ai.demo.project.service;

import org.springframework.stereotype.Service;
import com.eugenscobich.ai.demo.project.entity.BookEntity;
import com.eugenscobich.ai.demo.project.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<BookEntity> getBooks() {
        return bookRepository.findAll();
    }
}