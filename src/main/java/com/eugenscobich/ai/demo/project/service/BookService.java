package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.entity.BookEntity;
import com.eugenscobich.ai.demo.project.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<BookEntity> getBooks() {
        return bookRepository.findAll();
    }

    public Optional<BookEntity> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public BookEntity createBook(BookEntity bookEntity) {
        return bookRepository.save(bookEntity);
    }

    public Optional<BookEntity> updateBook(Long id, BookEntity newBookData) {
        return bookRepository.findById(id).map(book -> {
            book.setName(newBookData.getName());
            book.setIsbn(newBookData.getIsbn());
            return bookRepository.save(book);
        });
    }

    public boolean deleteBook(Long id) {
        if (!bookRepository.existsById(id)) return false;
        bookRepository.deleteById(id);
        return true;
    }
}
