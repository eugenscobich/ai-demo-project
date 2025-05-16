package com.eugenscobich.ai.demo.project.repository;

import com.eugenscobich.ai.demo.project.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    // Additional query methods if necessary
}
