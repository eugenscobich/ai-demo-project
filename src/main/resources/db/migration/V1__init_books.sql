-- Migration V1: Initial creation of books table for BookEntity
CREATE TABLE IF NOT EXISTS books (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    isbn VARCHAR(255) NOT NULL UNIQUE
);
