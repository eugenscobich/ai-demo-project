package com.eugenscobich.ai.demo.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String isbn;
    
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private AuthorEntity author;

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public AuthorEntity getAuthor() {
        return author;
    }
    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }
}