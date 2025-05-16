package com.eugenscobich.ai.demo.project.controller;

import com.eugenscobich.ai.demo.project.model.Book;
import com.eugenscobich.ai.demo.project.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    @WithMockUser(username = "admin", roles = {"USER"})
    void testGetBooks() throws Exception {
        Book book = new Book("1", "Test Title", "Test Author");
        when(bookService.getBooks()).thenReturn(Collections.singletonList(book));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].title").value("Test Title"))
                .andExpect(jsonPath("$[0].author").value("Test Author"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER"})
    void testGetBookById() throws Exception {
        Book book = new Book("2", "Hello", "Author A");
        when(bookService.getBookById("2")).thenReturn(book);

        mockMvc.perform(get("/api/books/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("2"))
                .andExpect(jsonPath("$.title").value("Hello"))
                .andExpect(jsonPath("$.author").value("Author A"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER"})
    void testCreateBook() throws Exception {
        Book createdBook = new Book("3", "Create Title", "Create Author");
        when(bookService.createBook(ArgumentMatchers.any(Book.class))).thenReturn(createdBook);
        String json = "{\"title\":\"Create Title\",\"author\":\"Create Author\"}";
        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("3"))
                .andExpect(jsonPath("$.title").value("Create Title"))
                .andExpect(jsonPath("$.author").value("Create Author"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER"})
    void testUpdateBook() throws Exception {
        Book updatedBook = new Book("2", "Updated", "Upd Author");
        when(bookService.updateBook(org.mockito.ArgumentMatchers.eq("2"), ArgumentMatchers.any(Book.class))).thenReturn(updatedBook);
        String json = "{\"title\":\"Updated\",\"author\":\"Upd Author\"}";
        mockMvc.perform(put("/api/books/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("2"))
                .andExpect(jsonPath("$.title").value("Updated"))
                .andExpect(jsonPath("$.author").value("Upd Author"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER"})
    void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/api/books/4")
                .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isOk());
    }
}
