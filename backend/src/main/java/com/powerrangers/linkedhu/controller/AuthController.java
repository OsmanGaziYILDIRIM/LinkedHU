package com.powerrangers.linkedhu.controller;


import com.powerrangers.linkedhu.dto.LoginRequestDTO;
import com.powerrangers.linkedhu.dto.LoginResponseDTO;
import com.powerrangers.linkedhu.dto.RegisterRequestDTO;
import com.powerrangers.linkedhu.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PreAuthorize("permitAll")
    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest){
        return authService.login(loginRequest);
    }

    @PreAuthorize("permitAll")
    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        return authService.register(registerRequestDTO);
    }
}
