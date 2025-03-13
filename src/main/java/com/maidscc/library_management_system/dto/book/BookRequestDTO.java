package com.maidscc.library_management_system.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDTO {

    @NotNull(message = "Title is required")
    @Size(min = 3, max = 255, message = "Title must be between 3 and 255 characters")
    private String title;

    @NotNull(message = "Author is required")
    @Size(min = 3, max = 100, message = "Author name must be between 3 and 100 characters")
    private String author;

    @Min(value = 1000, message = "Publication year must be after 1000")
    @Max(value = 2025, message = "Publication year cannot be in the future")
    private Integer publicationYear;

    @NotNull(message = "ISBN is required")
    @Size(min = 10, max = 13, message = "ISBN must be between 10 and 13 characters")
    private String isbn;
}
