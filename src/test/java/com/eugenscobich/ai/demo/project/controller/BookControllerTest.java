package com.eugenscobich.ai.demo.project.controller;

import com.eugenscobich.ai.demo.project.model.Book;
import com.eugenscobich.ai.demo.project.model.BookEntity;
import com.eugenscobich.ai.demo.project.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BookControllerTest {
    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void testGetBooks() throws Exception {
        List<Book> books = Arrays.asList(new Book("Book 1", "Author 1"), new Book("Book 2", "Author 2"));
        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Book 1")))
                .andExpect(jsonPath("$[1].author", is("Author 2")));
    }

    @Test
    void testGetBookById_Found() throws Exception {
        BookEntity entity = new BookEntity();
        entity.setId(1L);
        entity.setName("Book 1");
        entity.setIsbn("ISBN-001");
        when(bookService.getBookById(1L)).thenReturn(Optional.of(entity));

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Book 1")))
                .andExpect(jsonPath("$.isbn", is("ISBN-001")));
    }

    @Test
    void testGetBookById_NotFound() throws Exception {
        when(bookService.getBookById(2L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/books/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateBook() throws Exception {
        BookEntity request = new BookEntity();
        request.setName("Book 2");
        request.setIsbn("ISBN-002");
        BookEntity saved = new BookEntity();
        saved.setId(10L);
        saved.setName("Book 2");
        saved.setIsbn("ISBN-002");
        when(bookService.createBook(org.mockito.Mockito.any(BookEntity.class))).thenReturn(saved);

        String json = "{\"name\": \"Book 2\", \"isbn\": \"ISBN-002\"}";
        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.name", is("Book 2")))
                .andExpect(jsonPath("$.isbn", is("ISBN-002")));
    }

    @Test
    void testUpdateBook_Found() throws Exception {
        BookEntity updated = new BookEntity();
        updated.setId(1L);
        updated.setName("Book Updated");
        updated.setIsbn("ISBN-NEW");
        when(bookService.updateBook(eq(1L), org.mockito.Mockito.any(BookEntity.class))).thenReturn(Optional.of(updated));

        String json = "{\"name\": \"Book Updated\", \"isbn\": \"ISBN-NEW\"}";
        mockMvc.perform(put("/api/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Book Updated")))
                .andExpect(jsonPath("$.isbn", is("ISBN-NEW")));
    }

    @Test
    void testUpdateBook_NotFound() throws Exception {
        when(bookService.updateBook(eq(2L), org.mockito.Mockito.any(BookEntity.class))).thenReturn(Optional.empty());
        String json = "{\"name\": \"Book Updated\", \"isbn\": \"ISBN-NEW\"}";
        mockMvc.perform(put("/api/books/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteBook_Found() throws Exception {
        when(bookService.deleteBook(1L)).thenReturn(true);
        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteBook_NotFound() throws Exception {
        when(bookService.deleteBook(2L)).thenReturn(false);
        mockMvc.perform(delete("/api/books/2"))
                .andExpect(status().isNotFound());
    }
}