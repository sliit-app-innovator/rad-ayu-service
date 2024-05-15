package com.sliit.ayu.ayuservice.repository;

import com.sliit.ayu.ayuservice.model.StockTransferItemEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StockTransferDetailRepository extends CrudRepository<StockTransferItemEntity, Integer> {
    @Transactional
    @Query(value =DbQuery.GET_ALL_STOCK_TRANSFER_ITEMS_BY_ID, nativeQuery = true)
    StockTransferItemEntity getById();

    @Transactional
    @Query(value =DbQuery.GET_ALL_STOCK_TRANSFER_ITEMS_BY_TRANSFER_ID, nativeQuery = true)
    List<StockTransferItemEntity> getByTransferId(int transferId);

    @Transactional
    @Query(value =DbQuery.GET_STOCK_TRANSFER_ITEMS_BY_TRANSFER_ID_AND_MEDICINE_ID, nativeQuery = true)
    StockTransferItemEntity getByTransferIdAndMedicineId(int transferId, int medicineId);

    @Modifying
    @Query(value =DbQuery.DELETE_ALL_STOCK_TRANSFER_ITEMS_BY_TRANSFER_ID, nativeQuery = true)
    void deleteByTransferId(int transferId);
}
