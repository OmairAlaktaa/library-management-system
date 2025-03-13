package com.maidscc.library_management_system.controller.patron;

import com.maidscc.library_management_system.dto.patron.PatronRequestDTO;
import com.maidscc.library_management_system.dto.patron.PatronResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatronControllerTest {

    @Autowired
    private TestRestTemplate  restTemplate;

    private final String BASE_URL = "http://localhost:8080/api/patrons";

    @Test
    public void testGetAllPatrons() {
        ResponseEntity<PatronResponseDTO[]> responseEntity = restTemplate.getForEntity(BASE_URL, PatronResponseDTO[].class);

        assertNotNull(responseEntity, "Response entity is null");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), "Unexpected HTTP status code");

        PatronResponseDTO[] patrons = responseEntity.getBody();
        assertNotNull(patrons, "Response body is null");
        assertTrue(patrons.length > 0, "No patrons found");
    }

    @Test
    public void testGetPatronById() {
        int patronId = 1;
        ResponseEntity<PatronResponseDTO> responseEntity = restTemplate.getForEntity(BASE_URL + "/" + patronId, PatronResponseDTO.class);

        assertNotNull(responseEntity, "Response entity is null");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), "Unexpected HTTP status code");

        PatronResponseDTO patron = responseEntity.getBody();
        assertNotNull(patron, "Response body is null");
        assertEquals(patronId, patron.getPatronID(), "Patron ID does not match");
    }

    @Test
    public void testCreatePatron() {
        PatronRequestDTO newPatron = new PatronRequestDTO("Test", "User", "testuser@example.com", "1234567890");

        ResponseEntity<PatronResponseDTO> responseEntity = restTemplate.postForEntity(BASE_URL, newPatron, PatronResponseDTO.class);

        assertNotNull(responseEntity, "Response entity is null");
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode(), "Unexpected HTTP status code");

        PatronResponseDTO createdPatron = responseEntity.getBody();
        assertNotNull(createdPatron, "Response body is null");
        assertEquals(newPatron.getFirstName(), createdPatron.getFirstName(), "Patron first name does not match");
    }

    @Test
    public void testUpdatePatron() {
        int patronId = 1;
        PatronRequestDTO updatedPatron = new PatronRequestDTO("Updated", "User", "updateduser@example.com", "0987654321");

        ResponseEntity<PatronResponseDTO> responseEntity = restTemplate.exchange(
                BASE_URL + "/" + patronId,
                org.springframework.http.HttpMethod.PUT,
                new org.springframework.http.HttpEntity<>(updatedPatron),
                PatronResponseDTO.class
        );

        assertNotNull(responseEntity, "Response entity is null");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), "Unexpected HTTP status code");

        PatronResponseDTO updatedPatronResponse = responseEntity.getBody();
        assertNotNull(updatedPatronResponse, "Response body is null");
        assertEquals(updatedPatron.getFirstName(), updatedPatronResponse.getFirstName(), "Updated patron first name does not match");
    }

    @Test
    public void testDeletePatron() {
        int patronId = 1;

        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                BASE_URL + "/" + patronId,
                org.springframework.http.HttpMethod.DELETE,
                null,
                Void.class
        );

        assertNotNull(responseEntity, "Response entity is null");
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode(), "Unexpected HTTP status code");
    }
}
