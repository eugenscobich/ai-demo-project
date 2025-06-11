package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.model.Book;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class BookServiceTest {
    @Test
    void getAllBooks_returnsDummyList() {
        BookService bookService = new BookService();
        List<Book> books = bookService.getAllBooks();
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(3);
        assertThat(books.get(0).getTitle()).isEqualTo("Clean Code");
    }
}
