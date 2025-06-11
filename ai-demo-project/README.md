# AI Demo Project - Book Store Backend

This is a skeleton backend project for a book store, built using Java 21, Maven 3.8.6, and Spring Boot 3.4.4.

## Features
- Returns a list of dummy books as a REST endpoint (`/books`).

## Structure
- `BookService`: Returns a hardcoded list of books.
- `BookController`: REST API controller for books.

### Requirements
- Java 21
- Maven 3.8.6

### Build & Run
```bash
cd ai-demo-project
mvn spring-boot:run
```

Test endpoint:
```
curl http://localhost:8080/books
```
