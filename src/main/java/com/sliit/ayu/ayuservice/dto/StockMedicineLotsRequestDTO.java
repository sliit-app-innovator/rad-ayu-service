package com.sliit.ayu.ayuservice.dto;

import lombok.Data;

@Data
public class StockMedicineLotsRequestDTO {
    private Integer lotId;
    private String lotNum;
    private Integer issueQty;
    private String expiryDate;
    private String purchaseDate;
}
