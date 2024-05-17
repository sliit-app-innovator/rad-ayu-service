package com.sliit.ayu.ayuservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Top5ExpiringItemsResDto {
    private String name;
    private int qty;
    private String lotNumber;
    private String expiryDate;
    private String days;

    public Top5ExpiringItemsResDto(String name, int qty, String expiryDate,  int days,String lotNumber) {
        this.name = name;
        this.qty = qty;
        this.lotNumber = String.valueOf(lotNumber);
        this.expiryDate = expiryDate;
        this.days = String.valueOf(days);

    }
}
