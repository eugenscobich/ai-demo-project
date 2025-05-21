# Changelog

## [Unreleased]
### Added
- JKNIG-2: Integrated PostgreSQL 17 with Spring Data JPA
- Added BookEntity with columns: id (sequence), name, isbn
- Created BookRepository for accessing BookEntity
- Updated BookService to retrieve books from the database repository
- Added base database configuration in `application.properties`
