package com.example.AstrotalkAssignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "patient")
public class PatientEntity {
    @Id
    @Column(name = "patientId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "age", nullable = false)
    private Integer age;
    @Column(name = "room", nullable = false)
    private String room;
    @Column(name = "doctorName", nullable = false)
    private String doctorName;
    @Column(name = "admitDate")
    private Date admitDate;
    @Column(name = "expenses", nullable = false)
    private String expenses;
    @Column(name = "email", nullable = true)
    private String email;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "mobile", nullable = true)
    private String mobile;
}
