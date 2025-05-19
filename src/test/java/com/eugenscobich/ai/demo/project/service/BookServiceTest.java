package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.entity.BookEntity;
import com.eugenscobich.ai.demo.project.model.Book;
import com.eugenscobich.ai.demo.project.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;
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
    void testGetBooks() {
        BookEntity entity = BookEntity.builder().id(1L).name("Title1").isbn("ISBN1").build();
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(entity));
        List<Book> books = bookService.getBooks();
        assertEquals(1, books.size());
        assertEquals("Title1", books.get(0).getTitle());
        assertEquals("ISBN1", books.get(0).getAuthor());
    }

    @Test
    void testGetBookById_Found() {
        BookEntity entity = BookEntity.builder().id(11L).name("BookTitle").isbn("BookAuthor").build();
        when(bookRepository.findById(11L)).thenReturn(Optional.of(entity));
        Book book = bookService.getBookById("11");
        assertNotNull(book);
        assertEquals("BookTitle", book.getTitle());
        assertEquals("BookAuthor", book.getAuthor());
        assertEquals("11", book.getId());
    }

    @Test
    void testGetBookById_NotFound() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());
        Book book = bookService.getBookById("999");
        assertNull(book);
    }

    @Test
    void testCreateBook() {
        Book input = new Book(null, "CreateTitle", "CreateAuthor");
        BookEntity entity = BookEntity.builder().name("CreateTitle").isbn("CreateAuthor").build();
        BookEntity saved = BookEntity.builder().id(3L).name("CreateTitle").isbn("CreateAuthor").build();
        when(bookRepository.save(ArgumentMatchers.any(BookEntity.class))).thenReturn(saved);
        Book result = bookService.createBook(input);
        assertNotNull(result);
        assertEquals("CreateTitle", result.getTitle());
        assertEquals("CreateAuthor", result.getAuthor());
        assertEquals("3", result.getId());
    }

    @Test
    void testUpdateBook_Found() {
        Book input = new Book(null, "UpdatedTitle", "UpdatedAuthor");
        BookEntity existing = BookEntity.builder().id(5L).name("Old").isbn("Old").build();
        BookEntity updated = BookEntity.builder().id(5L).name("UpdatedTitle").isbn("UpdatedAuthor").build();
        when(bookRepository.findById(5L)).thenReturn(Optional.of(existing));
        when(bookRepository.save(ArgumentMatchers.any(BookEntity.class))).thenReturn(updated);
        Book result = bookService.updateBook("5", input);
        assertNotNull(result);
        assertEquals("UpdatedTitle", result.getTitle());
        assertEquals("UpdatedAuthor", result.getAuthor());
        assertEquals("5", result.getId());
    }

    @Test
    void testUpdateBook_NotFound() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());
        Book input = new Book(null, "X", "Y");
        Book result = bookService.updateBook("1234", input);
        assertNull(result);
    }

    @Test
    void testDeleteBook() {
        doNothing().when(bookRepository).deleteById(10L);
        bookService.deleteBook("10");
        verify(bookRepository, times(1)).deleteById(10L);
    }
}