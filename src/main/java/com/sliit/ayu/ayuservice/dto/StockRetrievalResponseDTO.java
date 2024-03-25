package com.sliit.ayu.ayuservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import lombok.Data;

@Data
@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockRetrievalResponseDTO {
    int stockReceivedId;

    public StockRetrievalResponseDTO(Integer id) {
        stockReceivedId =id;
    }
}
