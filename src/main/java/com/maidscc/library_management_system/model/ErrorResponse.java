package com.maidscc.library_management_system.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private String details;
    private int statusCode;


    public ErrorResponse(String message, String details, int statusCode) {
        this.message = message;
        this.details = details;
        this.statusCode = statusCode;
    }
}
