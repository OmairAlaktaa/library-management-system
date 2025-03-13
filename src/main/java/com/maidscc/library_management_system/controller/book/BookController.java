package com.maidscc.library_management_system.controller.book;

import com.maidscc.library_management_system.dto.book.BookRequestDTO;
import com.maidscc.library_management_system.dto.book.BookResponseDTO;
import com.maidscc.library_management_system.service.book.BookService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Integer bookId) {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRequestDTO bookRequest) {
        BookResponseDTO createdBook = bookService.createBook(bookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }


    @PutMapping("/{bookId}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Integer bookId, @RequestBody BookRequestDTO bookRequest) {
        return ResponseEntity.ok(bookService.updateBook(bookId, bookRequest));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }

}