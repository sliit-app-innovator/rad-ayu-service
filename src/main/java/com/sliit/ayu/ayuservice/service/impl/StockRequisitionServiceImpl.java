package com.sliit.ayu.ayuservice.service.impl;

import com.sliit.ayu.ayuservice.Utils;
import com.sliit.ayu.ayuservice.constants.ErrorCode;
import com.sliit.ayu.ayuservice.constants.OrderStatus;
import com.sliit.ayu.ayuservice.dto.StockRequisitionDTO;
import com.sliit.ayu.ayuservice.dto.StockRequisitionItemDTO;
import com.sliit.ayu.ayuservice.execption.AyuException;
import com.sliit.ayu.ayuservice.model.StockRequisitionEntity;
import com.sliit.ayu.ayuservice.repository.StockRequisitionRepository;
import com.sliit.ayu.ayuservice.service.StockRequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class StockRequisitionServiceImpl implements StockRequisitionService {

    private StockRequisitionRepository stockRequisitionRepository;

    @Autowired
    public StockRequisitionServiceImpl(StockRequisitionRepository stockRequisitionRepository) {
        this.stockRequisitionRepository = stockRequisitionRepository;
    }

    @Override
    public StockRequisitionDTO requestStockRequisition(StockRequisitionDTO stockRequisitionDTO) {
        if (stockRequisitionDTO.getStatus() != null && !stockRequisitionDTO.getStatus().equalsIgnoreCase(OrderStatus.NEW.name())) {
            throw AyuException.builder().errorCode(ErrorCode.AU_003.getCode()).errorMessage(ErrorCode.AU_003.getMessage()).build();
        } else {
            stockRequisitionDTO.setReference(Utils.generateReference("AYU_R"));
            if(stockRequisitionDTO.getDate() == null) {
                stockRequisitionDTO.setDate(Calendar.getInstance().getTime());
            }
            stockRequisitionDTO.setCreatedDate(Calendar.getInstance().getTime());
            stockRequisitionDTO.setUpdatedDate(Calendar.getInstance().getTime());
            stockRequisitionRepository.save(stockRequisitionDTO.toEntity());
            return stockRequisitionRepository.findByReference(stockRequisitionDTO.getReference()).toDTO();
        }
    }

    @Override
    public List<StockRequisitionDTO> searchStockRequisitionRequest(int toStore, String requestedBy) {
        List<StockRequisitionDTO> entityList = new ArrayList<>();
        if (toStore != 0 && (requestedBy != null && !requestedBy.isEmpty())) {
            stockRequisitionRepository.searchByStoreIdAndRequestBy(toStore, requestedBy).forEach(entity -> entityList.add(entity.toDTO()));
        } else if(toStore != 0) {
            stockRequisitionRepository.searchByStoreId(toStore).forEach(entity -> entityList.add(entity.toDTO()));
        } else if (requestedBy != null && !requestedBy.isEmpty()) {
            stockRequisitionRepository.searchByRequestBy(requestedBy).forEach(entity -> entityList.add(entity.toDTO()));
        } else {
            stockRequisitionRepository.findAll().forEach(entity -> entityList.add(entity.toDTO()));
        }
        return entityList;
    }

    @Override
    public StockRequisitionDTO getStockRequisitionRequest(int id) {
        Optional<StockRequisitionEntity> optional = stockRequisitionRepository.findById(id);
        if(optional.isPresent()){
            return optional.get().toDTO();
        } else {
            throw AyuException.builder().errorCode(ErrorCode.AU_001.getCode()).errorMessage(ErrorCode.AU_001.getMessage()).build();
        }
    }

    @Override
    public StockRequisitionDTO updateStockRequisitionRequest(StockRequisitionDTO stockRequisitionDTO) {
        Optional<StockRequisitionEntity> optional = stockRequisitionRepository.findById(stockRequisitionDTO.getId());
        if (optional.isPresent()) {
            StockRequisitionEntity entity = optional.get();
            if (stockRequisitionDTO.getRequestedBy() != null && !stockRequisitionDTO.getRequestedBy().isEmpty()) {
                entity.setRequestedBy(stockRequisitionDTO.getRequestedBy());
            }
            if (stockRequisitionDTO.getApprovedBy() != null && !stockRequisitionDTO.getApprovedBy().isEmpty()) {
                entity.setApprovedBy(stockRequisitionDTO.getApprovedBy());
            }
            if (stockRequisitionDTO.getFromStore() != null && stockRequisitionDTO.getFromStore() != 0) {
                entity.setFromStore(stockRequisitionDTO.getFromStore());
            }

            if (stockRequisitionDTO.getToStore() != null && stockRequisitionDTO.getToStore() != 0) {
                entity.setToStore(stockRequisitionDTO.getToStore());
            }

            if (stockRequisitionDTO.getStatus() != null && !stockRequisitionDTO.getStatus().isEmpty()) {
                entity.setStatusId(OrderStatus.valueOf(stockRequisitionDTO.getStatus()).getId());
            }

            if (stockRequisitionDTO.getDescription() != null && !stockRequisitionDTO.getDescription().isEmpty()) {
                entity.setDescription(stockRequisitionDTO.getDescription());
            }

            entity.setUpdatedDate(new Date());
            stockRequisitionRepository.save(entity);
            optional = stockRequisitionRepository.findById(stockRequisitionDTO.getId());
            if (optional.isPresent()) {
                return optional.get().toDTO();
            } else {
                throw AyuException.builder().errorCode(ErrorCode.AU_001.getCode()).errorMessage(ErrorCode.AU_001.getMessage()).build();
            }
        } else {
            throw AyuException.builder().errorCode(ErrorCode.AU_001.getCode()).errorMessage(ErrorCode.AU_001.getMessage()).build();
        }
    }

    @Override
    public void deleteStockRequisitionRequest(int id) {
        Optional<StockRequisitionEntity> optional = stockRequisitionRepository.findById(id);
        if(optional.isPresent()){
            StockRequisitionEntity entity = optional.get();
            if (entity.getStatusId() != OrderStatus.NEW.getId()) {
                throw AyuException.builder().errorCode(ErrorCode.AU_003.getCode()).errorMessage(ErrorCode.AU_003.getMessage()).build();
            } else {
                stockRequisitionRepository.delete(optional.get());
            }
        } else {
            throw AyuException.builder().errorCode(ErrorCode.AU_001.getCode()).errorMessage(ErrorCode.AU_001.getMessage()).build();
        }
    }

    @Override
    public StockRequisitionItemDTO requestStockRequisitionItems(StockRequisitionItemDTO stockRequisitionItems) {
        return null;
    }

    @Override
    public List<StockRequisitionItemDTO> searchStockRequisitionRequestItems(int medicineId) {
        return null;
    }

    @Override
    public StockRequisitionItemDTO getStockRequisitionRequestItem(int id) {
        return null;
    }

    @Override
    public StockRequisitionItemDTO updateStockRequisitionRequestItem(StockRequisitionItemDTO stockRequisitionItemDTO) {
        return null;
    }

    @Override
    public void deleteStockRequisitionRequestItem(int id) {

    }
}
