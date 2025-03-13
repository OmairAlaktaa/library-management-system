package com.maidscc.library_management_system.repository.book_loan;

import com.maidscc.library_management_system.model.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan, Integer> {
    BookLoan findByBook_BookIDAndPatron_PatronID(Integer bookId, Integer patronId);
    
    boolean existsByBook_BookIDAndReturnDateIsNull(Integer bookId);
}
