package com.sliit.ayu.ayuservice.service.impl;

import com.sliit.ayu.ayuservice.dto.UserDTO;
import com.sliit.ayu.ayuservice.model.*;
import com.sliit.ayu.ayuservice.repository.*;
import com.sliit.ayu.ayuservice.utils.Utils;
import com.sliit.ayu.ayuservice.constants.ErrorCode;
import com.sliit.ayu.ayuservice.constants.OrderStatus;
import com.sliit.ayu.ayuservice.dto.StockTransferDTO;
import com.sliit.ayu.ayuservice.dto.StockTransferItemDTO;
import com.sliit.ayu.ayuservice.execption.AyuException;
import com.sliit.ayu.ayuservice.service.StockTransferService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class StockTransferServiceImpl implements StockTransferService {

    StockTransferRepository stockTransferRepository;
    StockRequisitionRepository stockRequisitionRepository;
    StockTransferDetailRepository stockTransferDetailRepository;
    private MedicineMovementRepository medicineMovementRepository;
    private UserRepository userRepository;
    private EmailServiceImpl emailService;

    @Autowired
    public StockTransferServiceImpl(StockTransferRepository stockTransferRepository,
                                    StockTransferDetailRepository stockTransferDetailRepository,
                                    EmailServiceImpl emailService,
                                    UserRepository userRepository,
                                    MedicineMovementRepository medicineMovementRepository,
                                    StockRequisitionRepository stockRequisitionRepository) {
        this.stockTransferRepository = stockTransferRepository;
        this.stockTransferDetailRepository = stockTransferDetailRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.medicineMovementRepository = medicineMovementRepository;
        this.stockRequisitionRepository = stockRequisitionRepository;
    }


    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public StockTransferDTO requestStockTransfer(StockTransferDTO stockTransferDTO) {
        StockRequisitionEntity stockRequisitionEntity = null;
        if (stockTransferDTO.getOrderId() != null && stockTransferDTO.getOrderId() != 0) {
            stockRequisitionEntity  = stockRequisitionRepository.findById(stockTransferDTO.getOrderId()).orElse(null);
        }

        if (stockRequisitionEntity != null && stockRequisitionEntity.getStatusId() ==OrderStatus.ACCEPTED.getId()) {
            throw AyuException.builder().errorCode(ErrorCode.AU_003.getCode()).errorMessage(ErrorCode.AU_003.getMessage()).build();
        }

        if (!(stockTransferDTO.getStatus().equalsIgnoreCase(OrderStatus.NEW.name())
                /*|| stockTransferDTO.getStatus().equalsIgnoreCase(OrderStatus.PARTIALLY_ACCEPTED.name())
                    || stockTransferDTO.getStatus().equalsIgnoreCase(OrderStatus.REJECTED.name())*/)) {
            throw AyuException.builder().errorCode(ErrorCode.AU_003.getCode()).errorMessage(ErrorCode.AU_003.getMessage()).build();
        } else if(stockTransferDTO.getItems().isEmpty()) {
            throw AyuException.builder().errorCode(ErrorCode.AU_004.getCode()).errorMessage(ErrorCode.AU_004.getMessage()).build();
        } else {
            stockTransferDTO.setReference(Utils.generateReference("AYU_T"));
            if(stockTransferDTO.getDate() == null) {
                stockTransferDTO.setDate(Calendar.getInstance().getTime());
            }
            stockTransferDTO.setCreatedDate(Calendar.getInstance().getTime());
            stockTransferDTO.setUpdatedDate(Calendar.getInstance().getTime());
            stockTransferRepository.save(stockTransferDTO.toEntity());
            StockTransferDTO stockTransferResponseDTO = stockTransferRepository.findByReference(stockTransferDTO.getReference()).toDTO();

            int transferId = stockTransferResponseDTO.getId();
            List<StockTransferItemDTO> transferItemDTOS = new ArrayList<>();
            stockTransferDTO.getItems().forEach(item -> {
                item.setStockTransferId(transferId);
                item.setCreatedDate(Calendar.getInstance().getTime());
                item.setUpdatedDate(Calendar.getInstance().getTime());
                stockTransferDetailRepository.save(item.toEntity());
                transferItemDTOS.add(stockTransferDetailRepository.getByTransferIdAndMedicineId(transferId, item.getMedicineId()).toDTO());

                item.getLots().forEach(lot -> {
                    // IN
                    MedicineMovementEntity medicineMovementEntity = new MedicineMovementEntity();
                    medicineMovementEntity.setMedicineId(item.getMedicineId());
                    medicineMovementEntity.setStoreId(stockTransferDTO.getToStore());
                    medicineMovementEntity.setInQty(lot.getIssueQty());
                    medicineMovementEntity.setDescription("STOCK_RETRIEVE");
                    medicineMovementEntity.setLotId(Boolean.TRUE.equals(item.getIsLot()) ? lot.getId() : 0);
                    medicineMovementEntity.setCreatedDate(Calendar.getInstance().getTime());
                    medicineMovementEntity.setUpdatedDate(Calendar.getInstance().getTime());
                    medicineMovementRepository.save(medicineMovementEntity);

                    // OUT
                    medicineMovementEntity = new MedicineMovementEntity();
                    medicineMovementEntity.setMedicineId(item.getMedicineId());
                    medicineMovementEntity.setStoreId(stockTransferDTO.getFromStore());
                    medicineMovementEntity.setOutQty(lot.getIssueQty());
                    medicineMovementEntity.setDescription("STOCK_TRANSFER");
                    medicineMovementEntity.setLotId(Boolean.TRUE.equals(item.getIsLot()) ? lot.getId() : 0);
                    medicineMovementEntity.setCreatedDate(Calendar.getInstance().getTime());
                    medicineMovementEntity.setUpdatedDate(Calendar.getInstance().getTime());
                    medicineMovementRepository.save(medicineMovementEntity);
                });
            });

            if (stockRequisitionEntity != null) {
                stockRequisitionEntity.setStatusId(OrderStatus.ACCEPTED.getId());
                stockRequisitionRepository.save(stockRequisitionEntity);
            }

            stockTransferResponseDTO.setItems(transferItemDTOS);
            UserEntity user = userRepository.findByUsername(stockTransferDTO.getRequestedBy());
            if (user != null) {
                emailService.sendOrderComplete(user.toDTO(), stockTransferDTO);
            }

            return stockTransferResponseDTO;
        }
    }

    @Override
    public List<StockTransferDTO> searchStockTransferRequest(int toStore, String requestedBy) {
        List<StockTransferDTO> entityList = new ArrayList<>();
        if (toStore != 0 && (requestedBy != null && !requestedBy.isEmpty())) {
            stockTransferRepository.searchByStoreIdAndRequestBy(toStore, requestedBy).forEach(entity -> entityList.add(entity.toDTO()));
        } else if(toStore != 0) {
            stockTransferRepository.searchByStoreId(toStore).forEach(entity -> entityList.add(entity.toDTO()));
        } else if (requestedBy != null && !requestedBy.isEmpty()) {
            stockTransferRepository.searchByRequestBy(requestedBy).forEach(entity -> entityList.add(entity.toDTO()));
        } else {
            stockTransferRepository.findAll().forEach(entity -> entityList.add(entity.toDTO()));
        }
        return entityList;
    }

    @Override
    public StockTransferDTO getStockTransferRequest(int id) {
        Optional<StockTransferEntity> optional = stockTransferRepository.findById(id);
        if(optional.isPresent()){
            StockTransferEntity stockTransferEntity = optional.get();

            List<StockTransferItemEntity> stockTransferItemEntities = stockTransferDetailRepository.getByTransferId(stockTransferEntity.getId());
            List<StockTransferItemDTO> transferItemDTOS = new ArrayList<>();
            if (stockTransferItemEntities != null && !stockTransferItemEntities.isEmpty()) {
                transferItemDTOS =  stockTransferItemEntities.stream().map(StockTransferItemEntity::toDTO).toList();
            }
            StockTransferDTO response = optional.get().toDTO();
            response.setItems(transferItemDTOS);
            return response;
        } else {
            throw AyuException.builder().errorCode(ErrorCode.AU_001.getCode()).errorMessage(ErrorCode.AU_001.getMessage()).build();
        }
    }
}
