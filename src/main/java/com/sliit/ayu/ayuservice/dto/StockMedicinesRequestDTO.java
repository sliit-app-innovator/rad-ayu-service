package com.sliit.ayu.ayuservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class StockMedicinesRequestDTO {
    private Integer id;
    private String code;
    private Integer qty;
    private Boolean isLot;
    private List<StockMedicineLotsRequestDTO> lots;
}
