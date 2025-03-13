package com.maidscc.library_management_system.controller.book;

import com.maidscc.library_management_system.dto.book.BookRequestDTO;
import com.maidscc.library_management_system.dto.book.BookResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/api/books";

    @Test
    public void testGetAllBooks() {
        ResponseEntity<BookResponseDTO[]> responseEntity = restTemplate.getForEntity(BASE_URL, BookResponseDTO[].class);

        assertNotNull(responseEntity, "Response entity is null");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), "Unexpected HTTP status code");

        BookResponseDTO[] books = responseEntity.getBody();
        assertNotNull(books, "Response body is null");
        assertTrue(books.length > 0, "No books found");
    }

    @Test
    public void testGetBookById() {
        int bookId = 1;
        ResponseEntity<BookResponseDTO> responseEntity = restTemplate.getForEntity(BASE_URL + "/" + bookId, BookResponseDTO.class);

        assertNotNull(responseEntity, "Response entity is null");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), "Unexpected HTTP status code");

        BookResponseDTO book = responseEntity.getBody();
        assertNotNull(book, "Response body is null");
        assertEquals(bookId, book.getBookID(), "Book ID does not match");
    }

    @Test
    public void testCreateBook() {
        BookRequestDTO newBook = new BookRequestDTO("Test Title", "Test Author", 2024, "1234567890");

        ResponseEntity<BookResponseDTO> responseEntity = restTemplate.postForEntity(BASE_URL, newBook, BookResponseDTO.class);

        assertNotNull(responseEntity, "Response entity is null");
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode(), "Unexpected HTTP status code");

        BookResponseDTO createdBook = responseEntity.getBody();
        assertNotNull(createdBook, "Response body is null");
        assertEquals(newBook.getTitle(), createdBook.getTitle(), "Book title does not match");
    }

    @Test
    public void testUpdateBook() {
        int bookId = 1;
        BookRequestDTO updatedBook = new BookRequestDTO("Updated Title", "Updated Author", 2023, "0987654321");

        ResponseEntity<BookResponseDTO> responseEntity = restTemplate.exchange(
                BASE_URL + "/" + bookId,
                org.springframework.http.HttpMethod.PUT,
                new org.springframework.http.HttpEntity<>(updatedBook),
                BookResponseDTO.class
        );

        assertNotNull(responseEntity, "Response entity is null");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), "Unexpected HTTP status code");

        BookResponseDTO updatedBookResponse = responseEntity.getBody();
        assertNotNull(updatedBookResponse, "Response body is null");
        assertEquals(updatedBook.getTitle(), updatedBookResponse.getTitle(), "Updated book title does not match");
    }

    @Test
    public void testDeleteBook() {
        int bookId = 1;

        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                BASE_URL + "/" + bookId,
                org.springframework.http.HttpMethod.DELETE,
                null,
                Void.class
        );

        assertNotNull(responseEntity, "Response entity is null");
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode(), "Unexpected HTTP status code");
    }
}
