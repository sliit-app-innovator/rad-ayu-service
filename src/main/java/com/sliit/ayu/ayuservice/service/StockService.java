package com.sliit.ayu.ayuservice.service;

import com.sliit.ayu.ayuservice.dto.LotsResponseDto;
import com.sliit.ayu.ayuservice.dto.StockMedicineIssueRequestDTO;
import com.sliit.ayu.ayuservice.dto.StockRetrievalRequestDTO;
import com.sliit.ayu.ayuservice.dto.StockRetrievalResponseDTO;

import java.util.List;

public interface StockService {
    StockRetrievalResponseDTO stockRetrieval(StockRetrievalRequestDTO stockRetrievalRequestDTO);

    List<LotsResponseDto> getLotsByStoreAndMedicine(int storeId, int medicineId);

    StockRetrievalResponseDTO stockMedicineIssue(StockMedicineIssueRequestDTO stockMedicineIssueRequestDTO);
}
