package com.eugenscobich.ai.demo.project.controller;

import com.eugenscobich.ai.demo.project.model.Book;
import com.eugenscobich.ai.demo.project.model.BookEntity;
import com.eugenscobich.ai.demo.project.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("GET /api/books returns list of books")
    @WithMockUser
    void getBooks_returnsListOfBooks() throws Exception {
        List<Book> dummyBooks = Arrays.asList(new Book("Title A", "Author A"), new Book("Title B", "Author B"));
        Mockito.when(bookService.getAllBooks()).thenReturn(dummyBooks);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title").value("Title A"))
            .andExpect(jsonPath("$[0].author").value("Author A"))
            .andExpect(jsonPath("$[1].title").value("Title B"));
    }

    @Test
    @DisplayName("GET /api/books/{id} returns book when found")
    @WithMockUser
    void getBookById_found() throws Exception {
        BookEntity entity = new BookEntity();
        entity.setId(1L);
        entity.setName("Test Name");
        entity.setIsbn("ISBN1234");
        Mockito.when(bookService.getBookById(1L)).thenReturn(Optional.of(entity));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.name").value("Test Name"))
            .andExpect(jsonPath("$.isbn").value("ISBN1234"));
    }

    @Test
    @DisplayName("GET /api/books/{id} returns 404 when not found")
    @WithMockUser
    void getBookById_notFound() throws Exception {
        Mockito.when(bookService.getBookById(999L)).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/999"))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /api/books creates a book")
    @WithMockUser
    void createBook_createsBook() throws Exception {
        BookEntity toSave = new BookEntity();
        toSave.setName("New Book");
        toSave.setIsbn("NEWISBN");

        BookEntity saved = new BookEntity();
        saved.setId(10L);
        saved.setName("New Book");
        saved.setIsbn("NEWISBN");

        Mockito.when(bookService.createBook(any(BookEntity.class))).thenReturn(saved);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"New Book\",\"isbn\":\"NEWISBN\"}")
            .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(10L))
            .andExpect(jsonPath("$.name").value("New Book"));
    }

    @Test
    @DisplayName("PUT /api/books/{id} updates a book if present")
    @WithMockUser
    void updateBook_present() throws Exception {
        BookEntity updated = new BookEntity();
        updated.setId(2L);
        updated.setName("Updated Book");
        updated.setIsbn("UPDATEDISBN");
        Mockito.when(bookService.updateBook(eq(2L), any(BookEntity.class))).thenReturn(Optional.of(updated));
        mockMvc.perform(MockMvcRequestBuilders.put("/api/books/2")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Updated Book\",\"isbn\":\"UPDATEDISBN\"}")
            .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(2L))
            .andExpect(jsonPath("$.name").value("Updated Book"))
            .andExpect(jsonPath("$.isbn").value("UPDATEDISBN"));
    }

    @Test
    @DisplayName("PUT /api/books/{id} returns 404 if book not found")
    @WithMockUser
    void updateBook_notFound() throws Exception {
        Mockito.when(bookService.updateBook(eq(99L), any(BookEntity.class))).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.put("/api/books/99")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Ignored\",\"isbn\":\"IGNORED\"}")
            .with(csrf()))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /api/books/{id} returns 204 if deleted")
    @WithMockUser
    void deleteBook_deleted() throws Exception {
        Mockito.when(bookService.deleteBook(5L)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/5")
            .with(csrf()))
            .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE /api/books/{id} returns 404 if not found")
    @WithMockUser
    void deleteBook_notFound() throws Exception {
        Mockito.when(bookService.deleteBook(99L)).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/99")
            .with(csrf()))
            .andExpect(status().isNotFound());
    }
}
