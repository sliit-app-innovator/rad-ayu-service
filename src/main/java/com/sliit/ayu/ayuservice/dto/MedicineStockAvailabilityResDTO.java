package com.sliit.ayu.ayuservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineStockAvailabilityResDTO {
    private int id;
    private String name;
    private int availableStock;
    private double percentage;
}
