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
    void getBooks_returnsAllBooks() {
        BookEntity entity1 = BookEntity.builder().id(1L).name("Book 1").isbn("Author 1").build();
        BookEntity entity2 = BookEntity.builder().id(2L).name("Book 2").isbn("Author 2").build();
        when(bookRepository.findAll()).thenReturn(Arrays.asList(entity1, entity2));

        List<Book> books = bookService.getBooks();
        assertThat(books).hasSize(2);
        assertThat(books.get(0).getTitle()).isEqualTo("Book 1");
        assertThat(books.get(1).getTitle()).isEqualTo("Book 2");
    }

    @Test
    void getBookById_returnsBook_whenExists() {
        BookEntity entity = BookEntity.builder().id(1L).name("Book 1").isbn("Author 1").build();
        when(bookRepository.findById(1L)).thenReturn(Optional.of(entity));

        Book book = bookService.getBookById("1");
        assertThat(book).isNotNull();
        assertThat(book.getTitle()).isEqualTo("Book 1");
    }

    @Test
    void getBookById_returnsNull_whenNotExists() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        Book book = bookService.getBookById("1");
        assertThat(book).isNull();
    }

    @Test
    void createBook_savesAndReturnsBook() {
        Book toCreate = new Book(null, "Book 1", "Author 1");
        BookEntity entity = BookEntity.builder().name("Book 1").isbn("Author 1").build();
        BookEntity savedEntity = BookEntity.builder().id(1L).name("Book 1").isbn("Author 1").build();
        when(bookRepository.save(any(BookEntity.class))).thenReturn(savedEntity);

        Book created = bookService.createBook(toCreate);
        assertThat(created).isNotNull();
        assertThat(created.getId()).isEqualTo("1");
        assertThat(created.getTitle()).isEqualTo("Book 1");
        verify(bookRepository).save(any(BookEntity.class));
    }

    @Test
    void updateBook_returnsUpdatedBook_whenExists() {
        Book toUpdate = new Book("1", "Updated", "Author 1");
        BookEntity entity = BookEntity.builder().id(1L).name("Book 1").isbn("Author 1").build();
        BookEntity updatedEntity = BookEntity.builder().id(1L).name("Updated").isbn("Author 1").build();
        when(bookRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(bookRepository.save(any(BookEntity.class))).thenReturn(updatedEntity);

        Book updated = bookService.updateBook("1", toUpdate);
        assertThat(updated).isNotNull();
        assertThat(updated.getTitle()).isEqualTo("Updated");
        verify(bookRepository).save(any(BookEntity.class));
    }

    @Test
    void updateBook_returnsNull_whenNotExists() {
        Book toUpdate = new Book("1", "Updated", "Author 1");
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        Book updated = bookService.updateBook("1", toUpdate);
        assertThat(updated).isNull();
        verify(bookRepository, never()).save(any(BookEntity.class));
    }

    @Test
    void deleteBook_deletesById() {
        doNothing().when(bookRepository).deleteById(1L);
        bookService.deleteBook("1");
        verify(bookRepository).deleteById(1L);
    }
}