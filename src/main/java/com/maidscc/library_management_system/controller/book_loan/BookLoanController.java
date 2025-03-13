package com.maidscc.library_management_system.controller.book_loan;

import com.maidscc.library_management_system.dto.book_loan.BookLoanResponseDTO;
import com.maidscc.library_management_system.service.book_loan.BookLoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookLoanController {

    private final BookLoanService bookLoanService;

    public BookLoanController(BookLoanService bookLoanService) {
        this.bookLoanService = bookLoanService;
    }

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BookLoanResponseDTO> borrowBook(@PathVariable Integer bookId, @PathVariable Integer patronId) {
        return ResponseEntity.ok(bookLoanService.borrowBook(bookId, patronId));
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BookLoanResponseDTO> returnBook(@PathVariable Integer bookId, @PathVariable Integer patronId) {
        return ResponseEntity.ok(bookLoanService.returnBook(bookId, patronId));
    }
}
