package com.sliit.ayu.ayuservice.repository;

import com.sliit.ayu.ayuservice.model.StockRequisitionItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StockRequisitionItemRepository extends JpaRepository<StockRequisitionItemEntity, Integer> {

        @Transactional
        @Query(value =DbQuery.GET_ALL_STOCK_REQUISITION_ITEMS_BY_ID, nativeQuery = true)
        StockRequisitionItemEntity getById();

        @Transactional
        @Query(value =DbQuery.GET_ALL_STOCK_REQUISITION_ITEMS_BY_REQUISITION_ID, nativeQuery = true)
        List<StockRequisitionItemEntity> getByRequisitionId(int requisitionId);

        @Transactional
        @Query(value =DbQuery.GET_STOCK_REQUISITION_ITEMS_BY_REQUISITION_ID_AND_MEDICINE_ID, nativeQuery = true)
        StockRequisitionItemEntity getByRequisitionIdAndMedicineId(int requisitionId, int medicineId);
}
