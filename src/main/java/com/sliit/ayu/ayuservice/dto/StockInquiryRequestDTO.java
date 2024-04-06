package com.sliit.ayu.ayuservice.dto;

import lombok.Data;

@Data
public class StockInquiryRequestDTO {
    private int page;
    private int perPage;
    private String search;
    private int storeId;
}
