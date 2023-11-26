package com.example.AstrotalkAssignment.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class logInDTO {
    HttpStatus httpStatus;
    String message;
}
