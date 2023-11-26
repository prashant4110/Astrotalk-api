package com.example.AstrotalkAssignment.dto;

import lombok.*;
import java.util.Date;

@Builder
@Data
public class patientDetailDTO {
    @NonNull
    private String name;
    @NonNull
    private Integer age;
    @NonNull
    private String room;
    @NonNull
    private String doctorName;
    private Date admitDate;
    @NonNull
    private String expenses;
    @NonNull
    private String status;
    private String email;
    private String mobile;
}
