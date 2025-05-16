package com.eugenscobich.ai.demo.project.repository;

import com.eugenscobich.ai.demo.project.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
}