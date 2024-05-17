package com.sliit.ayu.ayuservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TransferLotDTO {
    private int id;
    private String store;
    private int quantity;
    private String lotNum;
    private Date expiryDate;
    private Date purchaseDate;
    private int issueQty;
}
