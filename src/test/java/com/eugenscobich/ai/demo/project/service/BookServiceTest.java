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
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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
        BookEntity entity = BookEntity.builder().id(1L).name("Book 1").isbn("Author 1").build();
        when(bookRepository.findAll()).thenReturn(Arrays.asList(entity));
        List<Book> books = bookService.getBooks();
        assertEquals(1, books.size());
        assertEquals("1", books.get(0).getId());
        assertEquals("Book 1", books.get(0).getTitle());
        assertEquals("Author 1", books.get(0).getAuthor());
    }

    @Test
    void testGetBookByIdFound() {
        BookEntity entity = BookEntity.builder().id(2L).name("Book 2").isbn("Author 2").build();
        when(bookRepository.findById(2L)).thenReturn(Optional.of(entity));
        Book book = bookService.getBookById("2");
        assertNotNull(book);
        assertEquals("2", book.getId());
        assertEquals("Book 2", book.getTitle());
        assertEquals("Author 2", book.getAuthor());
    }

    @Test
    void testGetBookByIdNotFound() {
        when(bookRepository.findById(3L)).thenReturn(Optional.empty());
        Book book = bookService.getBookById("3");
        assertNull(book);
    }

    @Test
    void testCreateBook() {
        Book book = new Book(null, "Create Book", "Author X");
        BookEntity entity = BookEntity.builder().name("Create Book").isbn("Author X").build();
        BookEntity savedEntity = BookEntity.builder().id(10L).name("Create Book").isbn("Author X").build();
        when(bookRepository.save(any(BookEntity.class))).thenReturn(savedEntity);
        Book created = bookService.createBook(book);
        assertNotNull(created.getId());
        assertEquals("10", created.getId());
        assertEquals("Create Book", created.getTitle());
        assertEquals("Author X", created.getAuthor());
    }

    @Test
    void testUpdateBookFound() {
        Book book = new Book(null, "Upd Book", "Upd Auth");
        BookEntity entity = BookEntity.builder().id(11L).name("Test Old").isbn("Old Auth").build();
        BookEntity updatedEntity = BookEntity.builder().id(11L).name("Upd Book").isbn("Upd Auth").build();
        when(bookRepository.findById(11L)).thenReturn(Optional.of(entity));
        when(bookRepository.save(any(BookEntity.class))).thenReturn(updatedEntity);
        Book updated = bookService.updateBook("11", book);
        assertNotNull(updated);
        assertEquals("11", updated.getId());
        assertEquals("Upd Book", updated.getTitle());
        assertEquals("Upd Auth", updated.getAuthor());
    }

    @Test
    void testUpdateBookNotFound() {
        Book book = new Book(null, "X", "Y");
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());
        Book updated = bookService.updateBook("99", book);
        assertNull(updated);
    }

    @Test
    void testDeleteBook() {
        doNothing().when(bookRepository).deleteById(77L);
        bookService.deleteBook("77");
        verify(bookRepository, times(1)).deleteById(77L);
    }
}
