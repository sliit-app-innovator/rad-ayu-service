package com.sliit.ayu.ayuservice.service;

import com.sliit.ayu.ayuservice.dto.*;

import java.util.List;

public interface StockService {
    StockRetrievalResponseDTO stockRetrieval(StockRetrievalRequestDTO stockRetrievalRequestDTO);

    List<LotsResponseDto> getLotsByStoreAndMedicine(LotsRequestDTO lotsRequestDTO);

    StockRetrievalResponseDTO stockMedicineIssue(StockMedicineIssueRequestDTO stockMedicineIssueRequestDTO);

    MedicineStockResponseDTO getMedicinesWithStock(StockInquiryRequestDTO stockInqueryRequestDTO);
}
