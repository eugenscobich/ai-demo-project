package com.eugenscobich.ai.demo.project.repository;

import com.eugenscobich.ai.demo.project.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
