package com.sliit.ayu.ayuservice.service;

import com.sliit.ayu.ayuservice.dto.StockTransferDTO;

import java.util.List;

public interface StockTransferService {
    StockTransferDTO requestStockTransfer(StockTransferDTO stockTransferDTO);
    List<StockTransferDTO> searchStockTransferRequest(int toStore, String requestedBy);
    StockTransferDTO getStockTransferRequest(int id);
}
