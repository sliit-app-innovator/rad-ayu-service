package com.sliit.ayu.ayuservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MedicineStockDTO {
    private Integer id;
    private String name;
    private String code;
    private Integer reorderLevel;
    private Integer medicineType;
    private String medicineTypeName;
    private Integer unit;
    private String unitName;
    private Integer quantity;
}
