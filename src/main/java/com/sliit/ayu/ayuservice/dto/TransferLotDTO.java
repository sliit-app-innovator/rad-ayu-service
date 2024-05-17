package com.sliit.ayu.ayuservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TransferLotDTO {
    private int id;
    private Integer lotId;
    private String store;
    private int quantity;
    private String lotNum;
    private Date expiryDate;
    private Date purchaseDate;
    private int issueQty;
}
