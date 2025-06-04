# ai-demo-project

Backend Java Spring Boot (3.4.4) application for a Book Store.

## Features
- Java 21, Maven 3.8.6+
- Spring Boot skeleton
- Dummy service returns static list of books
- REST API endpoint: `/api/books` (GET)

## Structure
- Model: `Book`
- Service: `BookService`
- Controller: `BookController`

## How to build/run

```
mvn clean install
java -jar target/ai-demo-project-0.0.1-SNAPSHOT.jar
```

## API Example
```
GET http://localhost:8080/api/books
```
