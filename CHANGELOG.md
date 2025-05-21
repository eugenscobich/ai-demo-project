# Changelog

## [Unreleased]
### Added
- JKNIG-5: Added Flyway database migration, initial migration for books table, configured Spring Boot to use migrations and validate with Hibernate.

### Added
- JKNIG-4: Added Spring Security with HTTP Basic authentication using in-memory credentials configured in application.yml. All endpoints now require authentication.
### Added
- JKNIG-3: Added CRUD operations (get by id, create, update, delete) endpoints for BookEntity in BookController and BookService.

### Added
- JKNIG-2: Added PostgreSQL database integration, BookEntity, repository, and updated BookService to fetch books from DB.
- Initial project skeleton using Spring Boot 3.4.4, Java 21 & Maven.
- Book model, service with dummy data.
- REST endpoint for listing books.