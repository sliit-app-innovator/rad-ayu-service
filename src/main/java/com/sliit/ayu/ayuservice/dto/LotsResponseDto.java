package com.sliit.ayu.ayuservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class LotsResponseDto {
    private int lotId;
    private int quantity;
    private String expiryDate;
    private String lotNum;
    private String purchaseDate;
}
