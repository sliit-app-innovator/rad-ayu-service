package com.sliit.ayu.ayuservice.repository;

import com.sliit.ayu.ayuservice.model.StockRequisitionEntity;
import com.sliit.ayu.ayuservice.model.StockTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StockTransferRepository extends JpaRepository<StockTransferEntity, Integer> {

        StockTransferEntity findByReference(String reference);

        @Transactional
        @Query(value =DbQuery.GET_ALL_STOCK_TRANSFER, nativeQuery = true)
        List<StockTransferEntity> getAll();

        @Transactional
        @Query(value =DbQuery.SEARCH_STOCK_TRANSFER_BY_REQUESTED_BY_QUERY, nativeQuery = true)
        List<StockTransferEntity> searchByRequestBy(String requestBy);

        @Transactional
        @Query(value =DbQuery.SEARCH_STOCK_TRANSFER_BY_STORE_ID_QUERY, nativeQuery = true)
        List<StockTransferEntity> searchByStoreId(int storeId);

        @Transactional
        @Query(value =DbQuery.SEARCH_STOCK_TRANSFER_BY_STORE_ID_AND_REQUESTED_BY_QUERY, nativeQuery = true)
        List<StockTransferEntity> searchByStoreIdAndRequestBy(int storeId, String requestBy);
}
