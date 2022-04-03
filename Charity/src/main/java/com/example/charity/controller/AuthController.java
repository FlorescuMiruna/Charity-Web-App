package com.example.charity.controller;

import com.example.charity.dto.request.LoginRequest;
import com.example.charity.dto.request.SignupRequest;

import com.example.charity.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@RestController
@RequestMapping("/api/auth")
public class AuthController {
    public final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

      return authService.authenticateUser(loginRequest);

    }

    @PostMapping("/signup/{myRole}")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, @PathVariable String myRole) {

        return authService.registerUser(signUpRequest,myRole);

    }
}