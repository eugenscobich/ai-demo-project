package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.model.Book;
import com.eugenscobich.ai.demo.project.model.BookEntity;
import com.eugenscobich.ai.demo.project.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
    void testGetAllBooks() {
        BookEntity be1 = new BookEntity();
        be1.setName("Book One");
        be1.setIsbn("1111");

        BookEntity be2 = new BookEntity();
        be2.setName("Book Two");
        be2.setIsbn("2222");

        when(bookRepository.findAll()).thenReturn(Arrays.asList(be1, be2));

        List<Book> books = bookService.getAllBooks();
        assertThat(books).hasSize(2);
        assertThat(books.get(0).getTitle()).isEqualTo("Book One");
        assertThat(books.get(0).getAuthor()).isEqualTo("1111");
    }

    @Test
    void testGetBookById_ReturnsBook() {
        BookEntity be = new BookEntity();
        be.setId(1L);
        be.setName("Book1");
        be.setIsbn("isbn1");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(be));

        Optional<BookEntity> result = bookService.getBookById(1L);
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Book1");
    }

    @Test
    void testGetBookById_NotFound() {
        when(bookRepository.findById(10L)).thenReturn(Optional.empty());
        Optional<BookEntity> result = bookService.getBookById(10L);
        assertThat(result).isEmpty();
    }

    @Test
    void testCreateBook() {
        BookEntity be = new BookEntity();
        be.setName("Book1");
        be.setIsbn("isbn1");
        when(bookRepository.save(any(BookEntity.class))).thenReturn(be);
        BookEntity created = bookService.createBook(be);
        assertThat(created.getName()).isEqualTo("Book1");
    }

    @Test
    void testUpdateBook_Found() {
        BookEntity original = new BookEntity();
        original.setId(1L);
        original.setName("Old");
        original.setIsbn("oldisbn");
        BookEntity updated = new BookEntity();
        updated.setName("New");
        updated.setIsbn("newisbn");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(original));
        when(bookRepository.save(any(BookEntity.class))).thenAnswer(inv -> inv.getArgument(0));

        Optional<BookEntity> result = bookService.updateBook(1L, updated);
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("New");
        assertThat(result.get().getIsbn()).isEqualTo("newisbn");
    }

    @Test
    void testUpdateBook_NotFound() {
        BookEntity updated = new BookEntity();
        updated.setName("New");
        updated.setIsbn("newisbn");
        when(bookRepository.findById(10L)).thenReturn(Optional.empty());
        Optional<BookEntity> result = bookService.updateBook(10L, updated);
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteBook_Found() {
        when(bookRepository.existsById(1L)).thenReturn(true);
        doNothing().when(bookRepository).deleteById(1L);
        boolean result = bookService.deleteBook(1L);
        assertThat(result).isTrue();
        verify(bookRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteBook_NotFound() {
        when(bookRepository.existsById(2L)).thenReturn(false);
        boolean result = bookService.deleteBook(2L);
        assertThat(result).isFalse();
        verify(bookRepository, never()).deleteById(anyLong());
    }
}
