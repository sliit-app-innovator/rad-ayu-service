package com.sliit.ayu.ayuservice.service.impl;

import com.sliit.ayu.ayuservice.dto.UserDTO;
import com.sliit.ayu.ayuservice.model.UserEntity;
import com.sliit.ayu.ayuservice.repository.UserRepository;
import com.sliit.ayu.ayuservice.dto.StockRequestDTO;
import com.sliit.ayu.ayuservice.utils.Utils;
import com.sliit.ayu.ayuservice.constants.ErrorCode;
import com.sliit.ayu.ayuservice.constants.OrderStatus;
import com.sliit.ayu.ayuservice.dto.StockRequisitionDTO;
import com.sliit.ayu.ayuservice.dto.StockRequisitionItemDTO;
import com.sliit.ayu.ayuservice.execption.AyuException;
import com.sliit.ayu.ayuservice.model.StockRequisitionEntity;
import com.sliit.ayu.ayuservice.repository.StockRequisitionItemRepository;
import com.sliit.ayu.ayuservice.repository.StockRequisitionRepository;
import com.sliit.ayu.ayuservice.service.StockRequisitionService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StockRequisitionServiceImpl implements StockRequisitionService {

    @Autowired
    private ModelMapper modelMapper;

    private StockRequisitionRepository stockRequisitionRepository;
    private StockRequisitionItemRepository stockRequisitionItemRepository;
    private UserRepository userRepository;
    private EmailServiceImpl emailService;

    @Autowired
    public StockRequisitionServiceImpl(StockRequisitionRepository stockRequisitionRepository,
                                       StockRequisitionItemRepository stockRequisitionItemRepository,
                                       EmailServiceImpl emailService,
                                       UserRepository userRepository) {
        this.stockRequisitionRepository = stockRequisitionRepository;
        this.stockRequisitionItemRepository = stockRequisitionItemRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public StockRequisitionDTO requestStockRequisition(StockRequisitionDTO stockRequisitionDTO) {
        if (stockRequisitionDTO.getStatus() != null && !stockRequisitionDTO.getStatus().equalsIgnoreCase(OrderStatus.NEW.name())) {
            throw AyuException.builder().errorCode(ErrorCode.AU_003.getCode()).errorMessage(ErrorCode.AU_003.getMessage()).build();
        } else if(stockRequisitionDTO.getItems() == null || stockRequisitionDTO.getItems().isEmpty()) {
            throw AyuException.builder().errorCode(ErrorCode.AU_004.getCode()).errorMessage(ErrorCode.AU_004.getMessage()).build();
        } else {
                        stockRequisitionDTO.setReference(Utils.generateReference("AYU_R"));
            if(stockRequisitionDTO.getDate() == null) {
                stockRequisitionDTO.setDate(Calendar.getInstance().getTime());
            }
            stockRequisitionDTO.setCreatedDate(Calendar.getInstance().getTime());
            stockRequisitionDTO.setUpdatedDate(Calendar.getInstance().getTime());
            stockRequisitionRepository.save(stockRequisitionDTO.toEntity());
            StockRequisitionDTO stockRequisitionResponseDTO = stockRequisitionRepository.findByReference(stockRequisitionDTO.getReference()).toDTO();

            int requisitionId = stockRequisitionResponseDTO.getId();
            List<StockRequisitionItemDTO> requisitionItemDTOS = new ArrayList<>();
            stockRequisitionDTO.getItems().forEach(item -> {
                item.setStockRequisitionId(requisitionId);
                item.setCreatedDate(Calendar.getInstance().getTime());
                item.setUpdatedDate(Calendar.getInstance().getTime());
                stockRequisitionItemRepository.save(item.toEntity());
                requisitionItemDTOS.add(stockRequisitionItemRepository.getByRequisitionIdAndMedicineId(requisitionId, item.getMedicineId()).toDTO());
            });
            UserDTO user = userRepository.findByUsername(stockRequisitionDTO.getRequestedBy()).toDTO();
            emailService.sendOrderReq(user, stockRequisitionDTO);
            stockRequisitionResponseDTO.setItems(requisitionItemDTOS);
            return stockRequisitionResponseDTO;
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
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void deleteStockRequisitionRequest(int id) {
        Optional<StockRequisitionEntity> optional = stockRequisitionRepository.findById(id);
        if(optional.isPresent()){
            StockRequisitionEntity entity = optional.get();
            if (entity.getStatusId() != OrderStatus.NEW.getId()) {
                throw AyuException.builder().errorCode(ErrorCode.AU_003.getCode()).errorMessage(ErrorCode.AU_003.getMessage()).build();
            } else {
                stockRequisitionRepository.delete(optional.get());
                stockRequisitionItemRepository.deleteByRequisitionId(id);
            }
        } else {
            throw AyuException.builder().errorCode(ErrorCode.AU_001.getCode()).errorMessage(ErrorCode.AU_001.getMessage()).build();
        }
    }

    @Override
    public List<StockRequestDTO> getPendingRequests() {
       return stockRequisitionRepository.getRequestByStatusId(1).stream().map(StockRequestDTO::new).collect(Collectors.toList());
    }
}
