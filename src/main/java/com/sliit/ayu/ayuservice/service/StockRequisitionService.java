package com.sliit.ayu.ayuservice.service;

import com.sliit.ayu.ayuservice.dto.StockRequisitionDTO;
import com.sliit.ayu.ayuservice.dto.StockRequisitionItemDTO;
import com.sliit.ayu.ayuservice.dto.StockRetrievalRequestDTO;
import com.sliit.ayu.ayuservice.dto.StockRetrievalResponseDTO;
import com.sliit.ayu.ayuservice.model.StockRequisitionEntity;

import java.util.List;

public interface StockRequisitionService {
    StockRequisitionDTO requestStockRequisition(StockRequisitionDTO stockRequisitionDTO);
    List<StockRequisitionDTO> searchStockRequisitionRequest(int toStore, String requestedBy);
    StockRequisitionDTO getStockRequisitionRequest(int id);
    StockRequisitionDTO updateStockRequisitionRequest(StockRequisitionDTO stockRequisitionDTO);
    void deleteStockRequisitionRequest(int id);

    StockRequisitionItemDTO requestStockRequisitionItems(StockRequisitionItemDTO stockRequisitionItems);
    List<StockRequisitionItemDTO> searchStockRequisitionRequestItems(int medicineId);
    StockRequisitionItemDTO getStockRequisitionRequestItem(int id);
    StockRequisitionItemDTO updateStockRequisitionRequestItem(StockRequisitionItemDTO stockRequisitionItemDTO);
    void deleteStockRequisitionRequestItem(int id);
}
