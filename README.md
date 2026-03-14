# BookStore REST API

Backend project for managing a bookstore domain (books, orders, and purchase requests), built during a Senla course.

## Project overview

This repository contains a multi-module Maven project:

- `core` — domain entities, DTOs, services, repositories, JSON utilities, and tests.
- `springweb` — Spring Boot web application layer (REST controllers, security, JWT auth, templates).

## Tech stack

- Java 17
- Maven (multi-module build)
- Spring Boot 3.x (Web, Security, Data JPA)
- Hibernate / JPA
- MySQL 8
- JWT (jjwt)
- Log4j2
- JUnit / Mockito / AssertJ

## Repository structure

```text
.
├── core/                  # Business/domain logic and persistence layer
├── springweb/             # Web/API layer (Spring Boot)
├── batfiles/              # SQL scripts (DDL/DML) + helper batch file
├── checkstyle/            # Checkstyle configuration
├── Books.csv              # Sample data file
├── Orders.csv             # Sample data file
└── Requests.csv           # Sample data file
```

## Prerequisites

- JDK 17+
- Maven 3.8+
- MySQL 8+

## Configuration

Main runtime config is in:

- `springweb/src/main/resources/application.yml`
- `database.properties`
- `config.properties`

### Default database settings

By default, the app expects:

- DB: `bookstore`
- URL: `jdbc:mysql://localhost:3306/bookstore`
- User: `root`
- Password: `Kogalym13`

> ⚠️ For real usage, move credentials to environment variables or secrets management.

## Database setup

1. Create database:

```sql
CREATE DATABASE bookstore;
```

2. Run schema script:

```bash
mysql -u root -p bookstore < batfiles/DDL.sql
```

3. (Optional) Load sample data:

```bash
mysql -u root -p bookstore < batfiles/DML.sql
```

## Build

From the project root:

```bash
mvn clean install
```

If you only need the web module:

```bash
mvn -pl springweb -am clean install
```

## Run

Start the Spring Boot app:

```bash
mvn -pl springweb spring-boot:run
```

Main class: `com.ruslan.Application`.

Default local URL (Spring Boot default):

- `http://localhost:8080/`

## Authentication and authorization

- Public endpoints: `/auth/**`
- JWT token should be sent in header:
    - `Authorization: Bearer <token>`

Access model (high level):

- `GET /books/**`, `GET /orders/**` — USER or ADMIN
- `GET /requests/**` — ADMIN
- mutating operations on books/orders/requests — ADMIN

## API quick reference

### Auth

- `POST /auth/login`
- `POST /auth/registration`

### Books

- `GET /books/all`
- `GET /books/{id}`
- `POST /books/`
- `PUT /books/`
- `PATCH /books/{id}`
- `DELETE /books/{id}`
- `PUT /books/add-to-stock/{id}`
- `GET /books/poor-purchased`

### Orders

- `GET /orders/all`
- `GET /orders/{id}`
- `POST /orders/`
- `PUT /orders/`
- `DELETE /orders/{id}`
- `PATCH /orders/change-status/{status}/{id}`
- `GET /orders/count-completed-orders-for-period/{from}/{till}`
- `GET /orders/completed-by-period/{from}/{till}`
- `POST /orders/createOrder?buyer=...&address=...&booksId=1&booksId=2`

### Requests

- `GET /requests/all`
- `GET /requests/{id}`
- `POST /requests/`
- `PUT /requests/`
- `DELETE /requests/{id}`
- `GET /requests/requests-sorted-by-number`
- `GET /requests/requests-sorted-by-alphabetically`
- `POST /requests/createReqeusts?bookId=...`

## Testing

Run all tests:

```bash
mvn test
```

Or only the core module tests:

```bash
mvn -pl core test
```

## Notes

- There are both SQL scripts and CSV files with sample data in the repository root.
- Some endpoint names/paths include historical typos (for example `createReqeusts`) and are documented as implemented.
