package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.entity.BookEntity;
import com.eugenscobich.ai.demo.project.model.Book;
import com.eugenscobich.ai.demo.project.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBooks_ReturnsBooks() {
        BookEntity entity = BookEntity.builder().id(1L).name("Book 1").isbn("Author 1").build();
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(entity));
        List<Book> books = bookService.getBooks();
        assertEquals(1, books.size());
        assertEquals("1", books.get(0).getId());
        assertEquals("Book 1", books.get(0).getTitle());
        assertEquals("Author 1", books.get(0).getAuthor());
    }

    @Test
    void testGetBookById_Found() {
        BookEntity entity = BookEntity.builder().id(2L).name("Name2").isbn("Author2").build();
        when(bookRepository.findById(2L)).thenReturn(Optional.of(entity));
        Book book = bookService.getBookById("2");
        assertNotNull(book);
        assertEquals("2", book.getId());
        assertEquals("Name2", book.getTitle());
        assertEquals("Author2", book.getAuthor());
    }

    @Test
    void testGetBookById_NotFound() {
        when(bookRepository.findById(3L)).thenReturn(Optional.empty());
        Book book = bookService.getBookById("3");
        assertNull(book);
    }

    @Test
    void testCreateBook() {
        Book book = new Book(null, "Title 4", "Author 4");
        BookEntity toSave = new BookEntity();
        toSave.setName("Title 4");
        toSave.setIsbn("Author 4");
        BookEntity saved = BookEntity.builder().id(4L).name("Title 4").isbn("Author 4").build();
        when(bookRepository.save(any(BookEntity.class))).thenReturn(saved);
        Book result = bookService.createBook(book);
        assertEquals("4", result.getId());
        assertEquals("Title 4", result.getTitle());
        assertEquals("Author 4", result.getAuthor());
    }

    @Test
    void testUpdateBook_Found() {
        Book book = new Book(null, "Upd", "UPDAUTH");
        BookEntity entity = BookEntity.builder().id(5L).name("Old").isbn("OLA").build();
        BookEntity updated = BookEntity.builder().id(5L).name("Upd").isbn("UPDAUTH").build();
        when(bookRepository.findById(5L)).thenReturn(Optional.of(entity));
        when(bookRepository.save(any(BookEntity.class))).thenReturn(updated);
        Book result = bookService.updateBook("5", book);
        assertNotNull(result);
        assertEquals("5", result.getId());
        assertEquals("Upd", result.getTitle());
        assertEquals("UPDAUTH", result.getAuthor());
    }

    @Test
    void testUpdateBook_NotFound() {
        Book book = new Book(null, "Upd", "UPDAUTH");
        when(bookRepository.findById(10L)).thenReturn(Optional.empty());
        Book result = bookService.updateBook("10", book);
        assertNull(result);
    }

    @Test
    void testDeleteBook() {
        doNothing().when(bookRepository).deleteById(6L);
        bookService.deleteBook("6");
        verify(bookRepository, times(1)).deleteById(6L);
    }
}
