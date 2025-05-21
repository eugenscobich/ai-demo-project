package com.eugenscobich.ai.demo.project.controller;

import com.eugenscobich.ai.demo.project.model.Book;
import com.eugenscobich.ai.demo.project.model.BookEntity;
import com.eugenscobich.ai.demo.project.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
@org.springframework.context.annotation.Import(com.eugenscobich.ai.demo.project.config.TestSecurityConfig.class)
class BookControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("GET /api/books should return list of books")
    void getBooksShouldReturnList() throws Exception {
        when(bookService.getAllBooks()).thenReturn(Arrays.asList(
            new Book("Book1", "Author1"), new Book("Book2", "Author2")
        ));

        mockMvc.perform(get("/api/books"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title").value("Book1"))
            .andExpect(jsonPath("$[1].title").value("Book2"));
    }

    @Test
    @DisplayName("GET /api/books/{id} should return BookEntity if present")
    void getBookByIdShouldReturnBook() throws Exception {
        BookEntity entity = new BookEntity();
        entity.setId(1L);
        entity.setName("Book1");
        entity.setIsbn("123-456");
        when(bookService.getBookById(1L)).thenReturn(Optional.of(entity));
        mockMvc.perform(get("/api/books/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.name").value("Book1"));
    }
    
    @Test
    @DisplayName("GET /api/books/{id} should return 404 if not present")
    void getBookByIdShouldReturn404() throws Exception {
        when(bookService.getBookById(100L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/books/100"))
            .andExpect(status().isNotFound());
    }
    
    @Test
    @DisplayName("POST /api/books should create BookEntity")
    void createBookShouldReturnCreatedBook() throws Exception {
        BookEntity toSave = new BookEntity();
        toSave.setName("Book2");
        toSave.setIsbn("222-333");
        BookEntity saved = new BookEntity();
        saved.setId(5L);
        saved.setName("Book2");
        saved.setIsbn("222-333");
        when(bookService.createBook(ArgumentMatchers.any(BookEntity.class))).thenReturn(saved);
        
        String json = "{\"name\": \"Book2\", \"isbn\": \"222-333\"}";
        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(5L))
            .andExpect(jsonPath("$.name").value("Book2"));
    }

    @Test
    @DisplayName("PUT /api/books/{id} should update and return BookEntity if found")
    void updateBookShouldReturnUpdated() throws Exception {
        BookEntity updated = new BookEntity();
        updated.setId(2L);
        updated.setName("BookUpdated");
        updated.setIsbn("333-444");
        when(bookService.updateBook(eq(2L), ArgumentMatchers.any(BookEntity.class)))
                .thenReturn(Optional.of(updated));

        String json = "{\"name\": \"BookUpdated\", \"isbn\": \"333-444\"}";
        mockMvc.perform(put("/api/books/2")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("BookUpdated"));
    }

    @Test
    @DisplayName("PUT /api/books/{id} should return 404 if not found")
    void updateBookShouldReturn404() throws Exception {
        when(bookService.updateBook(eq(50L), ArgumentMatchers.any(BookEntity.class)))
            .thenReturn(Optional.empty());
        String json = "{\"name\": \"Any\", \"isbn\": \"AnyISBN\"}";
        mockMvc.perform(put("/api/books/50")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isNotFound());
    }
    
    @Test
    @DisplayName("DELETE /api/books/{id} should return 204 if deleted")
    void deleteBookShouldReturn204() throws Exception {
        when(bookService.deleteBook(10L)).thenReturn(true);
        mockMvc.perform(delete("/api/books/10"))
            .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE /api/books/{id} should return 404 if not found")
    void deleteBookShouldReturn404() throws Exception {
        when(bookService.deleteBook(33L)).thenReturn(false);
        mockMvc.perform(delete("/api/books/33"))
            .andExpect(status().isNotFound());
    }
}