package com.maidscc.library_management_system.service.book_loan;

import com.maidscc.library_management_system.dto.book_loan.BookLoanResponseDTO;
import com.maidscc.library_management_system.model.Book;
import com.maidscc.library_management_system.model.BookLoan;
import com.maidscc.library_management_system.model.Patron;
import com.maidscc.library_management_system.repository.book.BookRepository;
import com.maidscc.library_management_system.repository.book_loan.BookLoanRepository;
import com.maidscc.library_management_system.repository.patron.PatronRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookLoanService {
    private final BookLoanRepository bookLoanRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    public BookLoanService(BookLoanRepository bookLoanRepository, BookRepository bookRepository, PatronRepository patronRepository) {
        this.bookLoanRepository = bookLoanRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    @Transactional
    public BookLoanResponseDTO borrowBook(Integer bookId, Integer patronId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        Patron patron = patronRepository.findById(patronId).orElseThrow(() -> new RuntimeException("Patron not found"));

        if (bookLoanRepository.existsByBook_BookIDAndReturnDateIsNull(bookId)) {
            throw new RuntimeException("Book is already borrowed");
        }

        BookLoan bookLoan = new BookLoan();
        bookLoan.setBook(book);
        bookLoan.setPatron(patron);
        bookLoan.setBorrowDate(LocalDateTime.now());
        bookLoan = bookLoanRepository.save(bookLoan);

        return new BookLoanResponseDTO(bookLoan.getLoanID(), bookLoan.getBorrowDate(), bookLoan.getReturnDate(), bookId,
                patronId);
    }

    @Transactional
    public BookLoanResponseDTO returnBook(Integer bookId, Integer patronId) {
        BookLoan bookLoan = bookLoanRepository.findByBook_BookIDAndPatron_PatronID(bookId, patronId);
        if (bookLoan == null) {
            throw new RuntimeException("Loan record not found");
        }
        if (bookLoan.getReturnDate() != null) {
            throw new RuntimeException("Book has already been returned");
        }

        bookLoan.setReturnDate(LocalDateTime.now());
        bookLoanRepository.save(bookLoan);

        return new BookLoanResponseDTO(bookLoan.getLoanID(), bookLoan.getBorrowDate(), bookLoan.getReturnDate(), bookId,
                patronId);
    }
}