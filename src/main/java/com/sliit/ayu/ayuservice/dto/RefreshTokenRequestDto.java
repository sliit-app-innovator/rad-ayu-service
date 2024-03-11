package com.sliit.ayu.ayuservice.dto;

import lombok.Data;

@Data
public class RefreshTokenRequestDto {
    private String username;
    private String refreshToken;
}
