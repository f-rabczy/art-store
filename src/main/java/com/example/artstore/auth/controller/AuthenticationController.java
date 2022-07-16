package com.example.artstore.auth.controller;

import com.example.artstore.auth.domain.CustomUserDetails;
import com.example.artstore.auth.dto.request.AuthenticationRequest;
import com.example.artstore.auth.dto.response.AuthenticationResponse;
import com.example.artstore.auth.service.JwtUserDetailsService;
import com.example.artstore.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        return ResponseEntity.ok(new AuthenticationResponse(jwtUtil.generateToken(userDetails)));
    }

}
