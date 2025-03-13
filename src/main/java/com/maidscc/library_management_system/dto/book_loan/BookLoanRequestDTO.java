package com.maidscc.library_management_system.dto.book_loan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookLoanRequestDTO {
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private Integer bookId;
    private Integer patronId;
}