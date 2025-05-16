# AI Demo Project - Book Store Backend

This project is a demo backend application for a books store. It uses Spring Boot, Java 21, and Maven. The backend manages books and provides a CRUD REST API.

## Features
- REST endpoints for managing books (`/api/books`)
- CRUD operations: Create, Read, Update, Delete books
- JPA entity integration with PostgreSQL
- Spring Security (HTTP Basic)
- Unit tests for controller and service layers (using JUnit + Mockito)

## Endpoints
- `GET /api/books` - Get all books
- `GET /api/books/{id}` - Get book by ID
- `POST /api/books` - Create new book
- `PUT /api/books/{id}` - Update existing book
- `DELETE /api/books/{id}` - Delete book by ID

## Technologies
- Java 21
- Spring Boot 3.4.x
- Maven
- PostgreSQL 17+
- Lombok
- JPA/Hibernate
- Spring Security (in-memory user)
- JUnit 5 & Mockito for tests

## Running locally
1. Clone the repository
2. Configure PostgreSQL (see `src/main/resources/application.yml`)
3. Build and run the app:
   ```bash
   mvn clean install && mvn spring-boot:run
   ```

## Tests
- Run tests using:
  ```bash
  mvn test
  ```
- Coverage: All controller endpoints and all service layer business logic are unit tested.

## Users
- Default credentials configured in `application.yml`

## Changelog
See `CHANGELOG.md` for detailed changes.

---
