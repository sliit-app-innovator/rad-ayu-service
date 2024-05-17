package com.sliit.ayu.ayuservice.service.impl;
import com.sliit.ayu.ayuservice.constants.TransactionDescriptions;
import com.sliit.ayu.ayuservice.dto.*;
import com.sliit.ayu.ayuservice.model.*;
import com.sliit.ayu.ayuservice.repository.*;
import com.sliit.ayu.ayuservice.service.StockService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private ModelMapper modelMapper;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private MedicineMovementRepository medicineMovementRepository;
    @Autowired
    private MedicineLotRepository medicineLotRepository;
    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private StockRetrievalRepository stockRetrievalRepository;
    @Autowired
    private StockRetrievalDetailRepository stockRetrievalDetailRepository;

    @Autowired
    private MedicineIssueRepository stockIssueRepository;

    @Autowired
    private MedicineIssueDetailRepository stockIssueDetailRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailServiceImpl emailService;


    @Override
    @Transactional
    public StockRetrievalResponseDTO stockRetrieval(StockRetrievalRequestDTO stockRetrievalRequestDTO) {
        Date now = Calendar.getInstance().getTime();
        stockRetrievalRequestDTO.setCreatedDate(now);
        stockRetrievalRequestDTO.setUpdatedDate(now);

        // Mapping and saving the StockRetrievalEntity
        StockRetrievalEntity stockRetrieval = stockRetrievalRepository.save(
                modelMapper.map(stockRetrievalRequestDTO, StockRetrievalEntity.class));
        List<MedicineMovementEntity> medicineMovementList = new LinkedList<>();

        stockRetrievalRequestDTO.getMedicineList().forEach(item -> {
            StockRetrievalDetailEntity  stockRetrievalDetailEntity = new StockRetrievalDetailEntity();
            stockRetrievalDetailEntity.setMedicineId(item.getId());
            stockRetrievalDetailEntity.setStockRetrievalId(stockRetrieval.getId());
            stockRetrievalDetailEntity.setQty(item.getQty());
            stockRetrievalDetailEntity.setCreatedDate(now);
            stockRetrievalDetailEntity.setUpdatedDate(now);

            StockRetrievalDetailEntity itemDetails = stockRetrievalDetailRepository.save(stockRetrievalDetailEntity);
            if(item.getIsExpire() && !item.getLots().isEmpty()){
                item.getLots().forEach(lotdetails->{
                    MedicineLotEntity lot = new MedicineLotEntity();
                    lot.setMedicineId(item.getId());
                    lot.setLotNum(item.getCode() + "-" + formatter.format(lotdetails.getExpireDate()));
                    lot.setQty(lotdetails.getExpireQty());
                    lot.setExpireDate(lotdetails.getExpireDate());
                    lot.setUpdatedDate(now);
                    lot.setCreatedDate(now);
                    MedicineLotEntity storedLot = medicineLotRepository.save(lot);

                    MedicineMovementEntity medicineMovement = new MedicineMovementEntity();
                    medicineMovement.setMedicineId(item.getId());
                    medicineMovement.setDescription(TransactionDescriptions.AU_STOCK_RECEIVE.getDescription());
                    medicineMovement.setInQty(lotdetails.getExpireQty());
                    medicineMovement.setOutQty(0);
                    medicineMovement.setLotId(storedLot.getId());
                    medicineMovement.setStoreId(stockRetrievalRequestDTO.getStoreId());
                    medicineMovement.setReferenceId(itemDetails.getId());
                    medicineMovement.setCreatedDate(now);
                    medicineMovement.setUpdatedDate(now);
                    medicineMovementList.add(medicineMovement);

                });
            }else{
                MedicineMovementEntity medicineMovement = new MedicineMovementEntity();
                medicineMovement.setMedicineId(item.getId());
                medicineMovement.setDescription(TransactionDescriptions.AU_STOCK_RECEIVE.getDescription());
                medicineMovement.setInQty(item.getQty());
                medicineMovement.setOutQty(0);
                medicineMovement.setStoreId(stockRetrievalRequestDTO.getStoreId());
                medicineMovement.setReferenceId(itemDetails.getId());
                medicineMovement.setCreatedDate(now);
                medicineMovement.setUpdatedDate(now);
                medicineMovementList.add(medicineMovement);
            }
        });


        if(!medicineMovementList.isEmpty()){
            medicineMovementRepository.saveAll(medicineMovementList);
        }
        UserDTO user = userRepository.findByUsername(stockRetrievalRequestDTO.getRequestBy()).toDTO();
        emailService.newInventory(user, stockRetrievalRequestDTO);
        return new StockRetrievalResponseDTO(stockRetrieval.getId(),true);
    }

    @Override
    public List<LotsResponseDto> getLotsByStoreAndMedicine(LotsRequestDTO lotsRequestDTO) {
        List<Object[]> results =  medicineMovementRepository.findAllByStoreIdAndMedicineId(lotsRequestDTO.getStoreId(),lotsRequestDTO.getMedicineId());
        return results.stream().map(result -> new LotsResponseDto(
                Integer.parseInt(result[0].toString()),
                result[1].toString(),
                Integer.parseInt(result[2].toString()),
                formatter.format((Date) result[3]),
                (String) result[4],
                formatter.format((Date) result[5])

        )).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public StockRetrievalResponseDTO stockMedicineIssue(StockMedicineIssueRequestDTO stockMedicineIssueRequestDTO) {
        Date now = Calendar.getInstance().getTime();
        stockMedicineIssueRequestDTO.setCreatedDate(now);
        stockMedicineIssueRequestDTO.setUpdatedDate(now);

        MedicineIssueEntity stockIssue = stockIssueRepository.save(
                modelMapper.map(stockMedicineIssueRequestDTO, MedicineIssueEntity.class));
        List<MedicineMovementEntity> medicineMovementList = new LinkedList<>();
        stockMedicineIssueRequestDTO.getMedicineList().stream().forEach(item -> {

            MedicineIssueDetailEntity stockIssueDetailEntity = new MedicineIssueDetailEntity();
            stockIssueDetailEntity.setMedicineId(item.getId());
            stockIssueDetailEntity.setMedicineIssueId(stockIssue.getId());
            stockIssueDetailEntity.setQty(item.getQty());
            stockIssueDetailEntity.setCreatedDate(now);
            stockIssueDetailEntity.setUpdatedDate(now);
            MedicineIssueDetailEntity itemDetails = stockIssueDetailRepository.save(stockIssueDetailEntity);
            if(item.getIsLot() && !item.getLots().isEmpty()){
                item.getLots().forEach(lot-> {
                    MedicineMovementEntity medicineMovement = new MedicineMovementEntity();
                    medicineMovement.setMedicineId(item.getId());
                    medicineMovement.setDescription(TransactionDescriptions.AU_STOCK_ISSUE_TO_PATIENT.getDescription());
                    medicineMovement.setInQty(0);
                    medicineMovement.setOutQty(lot.getIssueQty());
                    medicineMovement.setLotId(lot.getLotId());
                    medicineMovement.setStoreId(stockMedicineIssueRequestDTO.getStoreId());
                    medicineMovement.setReferenceId(itemDetails.getId());
                    medicineMovement.setCreatedDate(now);
                    medicineMovement.setUpdatedDate(now);
                    medicineMovementList.add(medicineMovement);
                });
            }
            else
            {
                MedicineMovementEntity medicineMovement = new MedicineMovementEntity();
                medicineMovement.setMedicineId(item.getId());
                medicineMovement.setDescription(TransactionDescriptions.AU_STOCK_ISSUE_TO_PATIENT.getDescription());
                medicineMovement.setInQty(0);
                medicineMovement.setOutQty(item.getQty());
                medicineMovement.setStoreId(stockMedicineIssueRequestDTO.getStoreId());
                medicineMovement.setReferenceId(itemDetails.getId());
                medicineMovement.setCreatedDate(now);
                medicineMovement.setUpdatedDate(now);
                medicineMovementList.add(medicineMovement);
            }
        });

        if(!medicineMovementList.isEmpty()){
            medicineMovementRepository.saveAll(medicineMovementList);
        }
        return new StockRetrievalResponseDTO(stockIssue.getId(),true);
    }

    @Override
    public MedicineStockResponseDTO getMedicinesWithStock(StockInquiryRequestDTO stockInqueryRequestDTO) {
        int limit = stockInqueryRequestDTO.getPerPage();
        int skip = (stockInqueryRequestDTO.getPage() - 1) * stockInqueryRequestDTO.getPerPage();
        MedicineStockResponseDTO responseDTO = new MedicineStockResponseDTO();
        List<Object[]> results =  medicineMovementRepository.findAllByStoreIdAndName(stockInqueryRequestDTO.getStoreId(),stockInqueryRequestDTO.getSearch(),limit,skip);
        List<MedicineStockDTO> medicineStockResponseDTOList = results.stream().map(result -> new MedicineStockDTO(
                Integer.parseInt(result[0].toString()),
                (String) result[1],
                (String) result[2],
                Integer.parseInt(result[3].toString()),
                Integer.parseInt(result[4].toString()),
                (String) result[5],
                Integer.parseInt(result[6].toString()),
                (String) result[7],
                Integer.parseInt(result[8].toString())
        )).collect(Collectors.toList());

        responseDTO.setData(medicineStockResponseDTOList);
        responseDTO.setPerPage(stockInqueryRequestDTO.getPerPage());
        responseDTO.setPage(stockInqueryRequestDTO.getPage());
        responseDTO.setTotal(medicineRepository.findAllByStoreIdAndNameCount(stockInqueryRequestDTO.getSearch()));
        responseDTO.setTotalPages(responseDTO.getTotal()/stockInqueryRequestDTO.getPage());

        return responseDTO;
    }


}
