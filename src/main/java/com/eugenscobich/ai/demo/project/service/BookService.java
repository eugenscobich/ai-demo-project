package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.model.Book;
import org.springframework.stereotype.Service;

import com.eugenscobich.ai.demo.project.entity.BookEntity;
import com.eugenscobich.ai.demo.project.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getBooks() {
        return bookRepository.findAll().stream()
            .map(this::toModel)
            .collect(Collectors.toList());
    }

    public Book getBookById(String id) {
        Optional<BookEntity> entity = bookRepository.findById(Long.parseLong(id));
        return entity.map(this::toModel).orElse(null);
    }

    public Book createBook(Book book) {
        BookEntity entity = new BookEntity();
        entity.setName(book.getTitle());
        entity.setIsbn(book.getAuthor());
        BookEntity saved = bookRepository.save(entity);
        return toModel(saved);
    }

    public Book updateBook(String id, Book book) {
        Optional<BookEntity> optionalEntity = bookRepository.findById(Long.parseLong(id));
        if(optionalEntity.isPresent()) {
            BookEntity entity = optionalEntity.get();
            entity.setName(book.getTitle());
            entity.setIsbn(book.getAuthor());
            BookEntity updated = bookRepository.save(entity);
            return toModel(updated);
        }
        return null;
    }

    public void deleteBook(String id) {
        bookRepository.deleteById(Long.parseLong(id));
    }

    private Book toModel(BookEntity entity) {
        return new Book(
                entity.getId() != null ? entity.getId().toString() : null,
                entity.getName(),
                entity.getIsbn()
        );
    }
}
