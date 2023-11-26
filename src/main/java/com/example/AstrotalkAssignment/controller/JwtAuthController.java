package com.example.AstrotalkAssignment.controller;

import com.example.AstrotalkAssignment.Auth.jwtUtils;
import com.example.AstrotalkAssignment.dto.jwtResponse;
import com.example.AstrotalkAssignment.dto.jwtRequest;
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
    private jwtUtils helper;

    private Logger logger = LoggerFactory.getLogger(JwtAuthController.class);


    @PostMapping("/login")
    public ResponseEntity<jwtResponse> login(@RequestBody jwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        String userDetails = userDetailsService.loadUserByUsername(request.getEmail()).toString();
        String token = this.helper.generate(userDetails,"User");

        jwtResponse response = jwtResponse.builder()
                .token(token)
                .email(request.getEmail()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
