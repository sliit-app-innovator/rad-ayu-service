package com.sliit.ayu.ayuservice.service.impl;
import com.sliit.ayu.ayuservice.constants.TransactionDescriptions;
import com.sliit.ayu.ayuservice.dto.StockRetrievalRequestDTO;
import com.sliit.ayu.ayuservice.dto.StockRetrievalResponseDTO;
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

        return new StockRetrievalResponseDTO(stockRetrieval.getId(),true);
    }


}
