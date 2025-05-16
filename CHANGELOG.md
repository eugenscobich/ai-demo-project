# Changelog

## [0.0.3] - 2025-05-16
### Added
- CRUD endpoints (get by id, create, update, delete) for BookEntity via BookController & BookService.

## [0.0.2] - 2025-05-16
### Added
- PostgreSQL 17 integration via application.properties and Maven dependency.
- JPA entity BookEntity with id (sequence), name, isbn fields.
- BookRepository interface using Spring Data JPA.
- BookService now loads book data from repository.

## [0.0.1] - 2025-05-16
### Added
- Used Lombok's @RequiredArgsConstructor in BookController, removed manual constructor injection.
- Initial project structure using Java 21, Maven 3.8.6, and Spring Boot 3.4.4.
- REST endpoint `/api/books` to retrieve a list of books.
- Service class with a method returning a dummy list of books.