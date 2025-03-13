// PatronResponseDTO.java
package com.maidscc.library_management_system.dto.patron;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatronResponseDTO {
    private Integer patronID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}