package com.maidscc.library_management_system.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDTO {

    private Integer bookID;
    private String title;
    private String author;
    private Integer publicationYear;
    private String isbn;


}
