package com.tmdna.jecom.controller;

import com.tmdna.jecom.entity.User;
import com.tmdna.jecom.security.dto.JwtResponse;
import com.tmdna.jecom.security.dto.LoginRequest;
import com.tmdna.jecom.security.dto.SignupRequest;
import com.tmdna.jecom.security.service.CustomUserDetailsService;
import com.tmdna.jecom.security.service.JwtTokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final CustomUserDetailsService userDetailsService;


    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenService jwtTokenService,
                          CustomUserDetailsService userDetailsService
                          ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginWithEmailAndPassword(@RequestBody @Valid LoginRequest request) {

        String token = getTokenFromEmailAndPassword(request.email(), request.password());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("signup")
    public ResponseEntity<?> signupWithEmailAndPassword(@RequestBody @Valid SignupRequest request) {
        User user = userDetailsService.reginterNewUser(request);

        String token = getTokenFromEmailAndPassword(request.email(), request.password());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private String getTokenFromEmailAndPassword(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        return jwtTokenService.generateToken(authentication);
    }
}
