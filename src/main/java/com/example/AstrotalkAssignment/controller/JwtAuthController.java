package com.example.AstrotalkAssignment.controller;

import com.example.AstrotalkAssignment.Auth.JwtUtils;
import com.example.AstrotalkAssignment.dto.CommonResponseDTO;
import com.example.AstrotalkAssignment.dto.JwtResponse;
import com.example.AstrotalkAssignment.dto.JwtRequest;
import com.example.AstrotalkAssignment.entity.EmployeeEntity;
import com.example.AstrotalkAssignment.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class JwtAuthController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    EmployeeService employeeService;

    @Autowired
    private JwtUtils helper;

    private Logger logger = LoggerFactory.getLogger(JwtAuthController.class);

//getting token here
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        String userDetails = userDetailsService.loadUserByUsername(request.getEmail()).toString();
        String token = this.helper.generate(userDetails, "User");

        JwtResponse response = JwtResponse.builder().token(token).email(request.getEmail()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/signUp", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeEntity req) throws Exception {
        boolean success = employeeService.saveRecord(req);
        if (success) {
            return new ResponseEntity<>(CommonResponseDTO.builder().status("User Registered Successfully").build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(CommonResponseDTO.builder().status("Failed").build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }
//manual exception class to handle exception
    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}

