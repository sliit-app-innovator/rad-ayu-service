package com.sliit.ayu.ayuservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import lombok.Data;

@Data
@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockRetrievalResponseDTO {
    int stockReceivedId;
    boolean status;

    public StockRetrievalResponseDTO(Integer id,boolean stst) {
        stockReceivedId =id;
        status =stst;
    }
}
