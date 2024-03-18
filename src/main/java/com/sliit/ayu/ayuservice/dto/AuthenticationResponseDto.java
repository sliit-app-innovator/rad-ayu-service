package com.sliit.ayu.ayuservice.dto;

import lombok.Data;

@Data
public class AuthenticationResponseDto {
    private String jwt;
    private String refreshToken;
    private String tokenType = "Bearer";

    public AuthenticationResponseDto(String jwt,String refreshToken) {
        this.jwt = jwt;
        this.refreshToken = refreshToken;
    }
}
