package com.eugenscobich.ai.demo.project.service;

import com.eugenscobich.ai.demo.project.model.Book;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    public List<Book> getBooks() {
        return Arrays.asList(
                new Book("The Hobbit", "J.R.R. Tolkien", "978-0261103344"),
                new Book("1984", "George Orwell", "978-0451524935"),
                new Book("Clean Code", "Robert C. Martin", "978-0132350884")
        );
    }
}
