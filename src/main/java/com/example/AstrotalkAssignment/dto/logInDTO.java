package com.example.AstrotalkAssignment.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class logInDTO {
    @NonNull
    private String email;
    @NonNull
    private String password;
}
