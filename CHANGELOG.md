# Changelog

## [JKNIG-5] Add unit test for controller layer
- Added unit tests for BookController in `BookControllerTest.java`

## [JKNIG-4] Add Spring Security feature
- Added `spring-boot-starter-security` to pom.xml
- Configured basic authentication with in-memory user in `SecurityConfig.java`
- Externalized username/password to application.properties

## [JKNIG-3] Add CRUD operations to Book Entity
- Added endpoints in `BookController` to get by id, create, update, delete `BookEntity`
- Implemented corresponding service methods in `BookService` for book CRUD operations