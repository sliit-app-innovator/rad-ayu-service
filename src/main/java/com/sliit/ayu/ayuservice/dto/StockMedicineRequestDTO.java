package com.sliit.ayu.ayuservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;

@Data
@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockMedicineRequestDTO {
    private Integer id;
    private Integer qty;
    private String  code;
    private Boolean isExpire;
    private List<StockMedicineExpireDatesRequestDTO> lots;
}
