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

        List<Integer> medicineIds = new LinkedList<>();
                stockRetrievalRequestDTO.getMedicineList().forEach(stockMedicineRequestDTO -> {
                    medicineIds.add(stockMedicineRequestDTO.getMedicineId());
        });

        Map<Integer, String> medicineCodeMap = medicineRepository.findAllById(medicineIds).stream()
                .collect(Collectors.toMap(MedicineEntity::getId, MedicineEntity::getCode));

        // Processing and saving Stock Retrieval Detail Entries
        List<StockRetrievalDetailEntity> detailEntryList = createDetailEntryList(stockRetrievalRequestDTO, stockRetrieval.getId(), now);
        List<StockRetrievalDetailEntity> retrievalDetailEntities = (List<StockRetrievalDetailEntity>) stockRetrievalDetailRepository.saveAll(detailEntryList);

        // Processing and saving Medicine Lots
        List<MedicineLotEntity> medicineLotEntityList = createMedicineLotList(stockRetrievalRequestDTO, medicineCodeMap, now);
        medicineLotRepository.saveAll(medicineLotEntityList);

        // Processing and saving Medicine Movements
        List<MedicineMovementEntity> medicineMovementEntityList = createMedicineMovementList(medicineLotEntityList, retrievalDetailEntities, stockRetrievalRequestDTO.getStoreId(), now);
        medicineMovementRepository.saveAll(medicineMovementEntityList);

        // Preparing the response
        return new StockRetrievalResponseDTO(stockRetrieval.getId());
    }

    private List<StockRetrievalDetailEntity> createDetailEntryList(StockRetrievalRequestDTO stockRetrievalRequestDTO, Integer stockRetrievalId, Date now) {
        return stockRetrievalRequestDTO.getMedicineList().stream().map(item -> {
            StockRetrievalDetailEntity detailEntry = new StockRetrievalDetailEntity();
            detailEntry.setMedicineId(item.getMedicineId());
            detailEntry.setQty(item.getQty());
            detailEntry.setStockRetrievalId(stockRetrievalId);
            detailEntry.setCreatedDate(now);
            detailEntry.setUpdatedDate(now);
            return detailEntry;
        }).collect(Collectors.toList());
    }

    private List<MedicineLotEntity> createMedicineLotList(StockRetrievalRequestDTO stockRetrievalRequestDTO, Map<Integer, String> medicineCodeMap, Date now) {
        return stockRetrievalRequestDTO.getExpireDates().stream().map(expireDate -> {
            String code = medicineCodeMap.getOrDefault(expireDate.getMedicineId(), "NA_CODE");
            MedicineLotEntity lot = new MedicineLotEntity();
            lot.setLotNum(code + "-" + expireDate.getExpireDate());
            lot.setQty(expireDate.getQty());
            lot.setMedicineId(expireDate.getMedicineId());
            lot.setExpireDate(expireDate.getExpireDate());
            lot.setCreatedDate(now);
            lot.setUpdatedDate(now);
            return lot;
        }).collect(Collectors.toList());
    }
    private List<MedicineMovementEntity> createMedicineMovementList(List<MedicineLotEntity> medicineLotEntityList, List<StockRetrievalDetailEntity> retrievalDetailEntities, Integer storeId, Date now) {
        return medicineLotEntityList.stream().map(lot -> {
            MedicineMovementEntity mEntity = new MedicineMovementEntity();
            mEntity.setInQty(lot.getQty());
            mEntity.setDescription(TransactionDescriptions.AU_STOCK_RECEIVE.getDescription());
            mEntity.setOutQty(0);
            mEntity.setLotId(lot.getId());
            mEntity.setMedicineId(lot.getMedicineId());
            mEntity.setCreatedDate(now);
            mEntity.setUpdatedDate(now);
            mEntity.setReferenceId(retrievalDetailEntities.stream()
                    .filter(en -> en.getMedicineId().equals(lot.getMedicineId()))
                    .findFirst()
                    .map(StockRetrievalDetailEntity::getId)
                    .orElseThrow(() -> new IllegalStateException("No matching StockRetrievalDetailEntity for Medicine ID " + lot.getMedicineId() + " found")));
            mEntity.setStoreId(storeId);
            return mEntity;
        }).collect(Collectors.toCollection(LinkedList::new));
    }
}
