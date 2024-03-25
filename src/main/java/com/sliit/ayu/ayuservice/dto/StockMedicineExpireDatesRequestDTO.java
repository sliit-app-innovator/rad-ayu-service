package com.sliit.ayu.ayuservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import lombok.Data;

import java.util.Date;

@Data
@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockMedicineExpireDatesRequestDTO {
    private Integer medicineId;
    private Integer qty;
    private Date expireDate;
}
