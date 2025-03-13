# Library Management System

## Overview
The **Library Management System** is a web-based application built with **Java Spring Boot** to streamline the management of books, members, and borrowing transactions. The system is designed to enhance efficiency in libraries by providing an easy-to-use interface for administrators and users.

## Features
- **Book Management**: Add, update, delete, and search for books.
- **Member Management**: Register and manage library members.
- **Borrowing & Returning**: Track book borrowings and returns.
- **User Authentication**: Secure login system with role-based access.
- **RESTful APIs**: Expose APIs for integration with other systems.

## Technologies Used
- **Backend**: Java Spring Boot
- **Database**: MySQL
- **ORM**: Hibernate / JPA
- **Security**: Spring Security with basic Authentication
- **Build Tool**: Maven

## Installation & Setup

### Prerequisites
Ensure you have the following installed:
- Java 17+
- PostgreSQL (or any SQL database)
- Maven
- Git

### Clone the Repository
```sh
git clone https://github.com/OmairAlaktaa/library-management-system.git
cd library-management-system
```

### Configure the Database
Update `application.properties` (or `application.yml`) with your database credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/library_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### Build & Run
```sh
mvn clean install
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints
| Method | Endpoint | Description |
|--------|---------|-------------|
| GET | `/books` | Get all books |
| POST | `/books` | Add a new book |
| GET | `/members` | Get all members |
| POST | `/borrow` | Borrow a book |
| POST | `/return` | Return a book |

(For full API documentation, you can find it under douc folder in project)

