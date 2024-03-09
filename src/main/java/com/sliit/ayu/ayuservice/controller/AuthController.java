package com.sliit.ayu.ayuservice.controller;

import com.sliit.ayu.ayuservice.dto.AuthenticationRequestDto;
import com.sliit.ayu.ayuservice.dto.AuthenticationResponseDto;
import com.sliit.ayu.ayuservice.dto.RefreshTokenRequestDto;
import com.sliit.ayu.ayuservice.security.CustomUserDetails;
import com.sliit.ayu.ayuservice.security.JwtUtil;
import com.sliit.ayu.ayuservice.service.impl.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ayu/service/v1/auth")
@Slf4j
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/token")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
            CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails);
            final String refreshToken = jwtUtil.generateRefreshToken(userDetails);
            // Store the refresh token
            customUserDetailsService.storeRefreshToken(userDetails.getUsername(), refreshToken);
            return ResponseEntity.ok(new AuthenticationResponseDto(jwt,refreshToken));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error during authentication: " + e.getMessage());
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequest) {
        String username = refreshTokenRequest.getUsername();
        String requestRefreshToken = refreshTokenRequest.getRefreshToken();
        if (customUserDetailsService.validateRefreshToken(username, requestRefreshToken)) {
            CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            final String jwt = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AuthenticationResponseDto(jwt,requestRefreshToken));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }
    }

}
