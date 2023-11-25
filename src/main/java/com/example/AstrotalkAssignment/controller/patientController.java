package com.example.AstrotalkAssignment.controller;

import com.example.AstrotalkAssignment.dto.commonResponseDTO;
import com.example.AstrotalkAssignment.dto.patientDetailDTO;
import com.example.AstrotalkAssignment.entity.PatientEntity;
import com.example.AstrotalkAssignment.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@Slf4j
public class patientController {
    @Autowired
    PatientService patientService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public commonResponseDTO getAllPatientList() throws Exception{
        List<PatientEntity> data=patientService.findListOfPatient();
        return commonResponseDTO.builder().data(data).status("Success").build();
    }
    @PostMapping(value = "/admit", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addPatient(@RequestBody PatientEntity req) throws Exception {
        boolean success = patientService.saveRecord(req);
        if(success) {
            return new ResponseEntity<>( commonResponseDTO.builder().status("Success").build(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(commonResponseDTO.builder().status("Failed").build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/discharge", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> dischargePatient(@RequestBody PatientEntity req) throws Exception {
        boolean success = patientService.dischargeRequest(req.getPatientId(),req.getStatus());
        if(success) {
            return new ResponseEntity<>( commonResponseDTO.builder().status("Success").build(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(commonResponseDTO.builder().status("Failed").build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
