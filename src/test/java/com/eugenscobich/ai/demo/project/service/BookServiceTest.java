package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.model.Book;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private final BookService bookService = new BookService();

    @Test
    void testGetBooksReturnsPredefinedList() {
        List<Book> books = bookService.getBooks();
        assertNotNull(books, "Books list should not be null");
        assertEquals(3, books.size(), "Books list should contain 3 books");

        Book book1 = books.get(0);
        Book book2 = books.get(1);
        Book book3 = books.get(2);

        assertEquals(1L, book1.getId());
        assertEquals("The Great Gatsby", book1.getTitle());
        assertEquals("F. Scott Fitzgerald", book1.getAuthor());

        assertEquals(2L, book2.getId());
        assertEquals("1984", book2.getTitle());
        assertEquals("George Orwell", book2.getAuthor());

        assertEquals(3L, book3.getId());
        assertEquals("To Kill a Mockingbird", book3.getTitle());
        assertEquals("Harper Lee", book3.getAuthor());
    }
}
