package com.eugenscobich.ai.demo.project.service;

import org.springframework.stereotype.Service;
import com.eugenscobich.ai.demo.project.entity.BookEntity;
import com.eugenscobich.ai.demo.project.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<BookEntity> getBooks() {
        return bookRepository.findAll();
    }
}