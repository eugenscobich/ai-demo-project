package com.eugenscobich.ai.demo.project.controller;

import com.eugenscobich.ai.demo.project.entity.BookEntity;
import com.eugenscobich.ai.demo.project.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;

    @Test
    void getAllBooks_shouldReturnBooksList() throws Exception {
        BookEntity book = new BookEntity(1L, "Book Title", "1234567890");
        Mockito.when(bookService.getBooks()).thenReturn(List.of(book));

        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Book Title"));
    }

    @Test
    void getBookById_exists_shouldReturnBook() throws Exception {
        BookEntity book = new BookEntity(1L, "Book Title", "1234567890");
        Mockito.when(bookService.getBookById(1L)).thenReturn(Optional.of(book));

        mockMvc.perform(MockMvcRequestBuilders.get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Book Title"));
    }

    @Test
    void getBookById_notFound_shouldReturn404() throws Exception {
        Mockito.when(bookService.getBookById(2L)).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.get("/books/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createBook_shouldReturnCreatedBook() throws Exception {
        BookEntity requestBook = new BookEntity(null, "New Book", "0987654321");
        BookEntity savedBook = new BookEntity(10L, "New Book", "0987654321");
        Mockito.when(bookService.createBook(any(BookEntity.class))).thenReturn(savedBook);

        String json = "{" +
                "\"name\":\"New Book\"," +
                "\"isbn\":\"0987654321\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(10L));
    }

    @Test
    void updateBook_exists_shouldReturnUpdatedBook() throws Exception {
        BookEntity requestBook = new BookEntity(null, "Updated Book", "1231231231");
        BookEntity updatedBook = new BookEntity(1L, "Updated Book", "1231231231");
        Mockito.when(bookService.updateBook(eq(1L), any(BookEntity.class))).thenReturn(Optional.of(updatedBook));

        String json = "{" +
                "\"name\":\"Updated Book\"," +
                "\"isbn\":\"1231231231\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Book"));
    }

    @Test
    void updateBook_notFound_shouldReturn404() throws Exception {
        Mockito.when(bookService.updateBook(eq(2L), any(BookEntity.class))).thenReturn(Optional.empty());
        String json = "{" +
                "\"name\":\"No Book\"," +
                "\"isbn\":\"0000000000\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/books/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteBook_exists_shouldReturn204() throws Exception {
        Mockito.when(bookService.deleteBook(1L)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteBook_notFound_shouldReturn404() throws Exception {
        Mockito.when(bookService.deleteBook(2L)).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/2"))
                .andExpect(status().isNotFound());
    }
}
