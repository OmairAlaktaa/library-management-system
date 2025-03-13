package com.maidscc.library_management_system.controller.book_loan;

import com.maidscc.library_management_system.dto.book_loan.BookLoanResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookLoanControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "/api";  // Use relative path, Spring Boot assigns a random port
    private static final String BORROW_URL = BASE_URL + "/borrow/{bookId}/patron/{patronId}";
    private static final String RETURN_URL = BASE_URL + "/return/{bookId}/patron/{patronId}";

    @Test
    public void testBorrowBook() {
        int bookId = 1;
        int patronId = 1;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<BookLoanResponseDTO> responseEntity = restTemplate.postForEntity(
                BORROW_URL, 
                requestEntity, 
                BookLoanResponseDTO.class, 
                bookId, 
                patronId
        );

        // Assertions
        assertNotNull(responseEntity, "Response entity is null");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), "Unexpected HTTP status code");

        BookLoanResponseDTO bookLoan = responseEntity.getBody();
        assertNotNull(bookLoan, "Response body is null");

        if (bookLoan != null) {
            assertEquals(bookId, bookLoan.getBookId(), "Book ID does not match");
            assertEquals(patronId, bookLoan.getPatronId(), "Patron ID does not match");
            assertNotNull(bookLoan.getBorrowDate(), "Borrow date should not be null");
        }
    }

    @Test
    public void testReturnBook() {
        int bookId = 9;
        int patronId = 9;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<BookLoanResponseDTO> responseEntity = restTemplate.exchange(
                RETURN_URL, 
                HttpMethod.PUT, 
                requestEntity, 
                BookLoanResponseDTO.class, 
                bookId, 
                patronId
        );

        // Assertions
        assertNotNull(responseEntity, "Response entity is null");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), "Unexpected HTTP status code");

        BookLoanResponseDTO bookLoan = responseEntity.getBody();
        assertNotNull(bookLoan, "Response body is null");

        if (bookLoan != null) {
            assertEquals(bookId, bookLoan.getBookId(), "Book ID does not match");
            assertEquals(patronId, bookLoan.getPatronId(), "Patron ID does not match");
            assertNotNull(bookLoan.getReturnDate(), "Return date should not be null");
        }
    }
}