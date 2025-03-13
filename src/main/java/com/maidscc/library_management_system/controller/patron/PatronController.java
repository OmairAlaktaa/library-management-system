// PatronController.java
package com.maidscc.library_management_system.controller.patron;

import com.maidscc.library_management_system.dto.patron.*;
import com.maidscc.library_management_system.service.patron.PatronService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    private final PatronService patronService;

    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping
    public ResponseEntity<List<PatronResponseDTO>> getAllPatrons() {
        return ResponseEntity.ok(patronService.getAllPatrons());
    }

    @GetMapping("/{patronId}")
    public ResponseEntity<PatronResponseDTO> getPatronById(@PathVariable Integer patronId) {
        return ResponseEntity.ok(patronService.getPatronById(patronId));
    }


    @PostMapping
    public ResponseEntity<PatronResponseDTO> createPatron(@RequestBody PatronRequestDTO patronRequest) {
        PatronResponseDTO createdPatron = patronService.createPatron(patronRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatron);
}

    @PutMapping("/{patronId}")
    public ResponseEntity<PatronResponseDTO> updatePatron(@PathVariable Integer patronId, @RequestBody PatronRequestDTO patronRequest) {
        return ResponseEntity.ok(patronService.updatePatron(patronId, patronRequest));
    }

    @DeleteMapping("/{patronId}")
    public ResponseEntity<Void> deletePatron(@PathVariable Integer patronId) {
        patronService.deletePatron(patronId);
        return ResponseEntity.noContent().build();
    }
}