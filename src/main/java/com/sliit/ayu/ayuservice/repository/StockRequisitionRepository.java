package com.sliit.ayu.ayuservice.repository;

import com.sliit.ayu.ayuservice.model.StockRequisitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StockRequisitionRepository extends JpaRepository<StockRequisitionEntity, Integer> {

        StockRequisitionEntity findByReference(String reference);

        @Transactional
        @Query(value =DbQuery.GET_ALL_STOCK_REQUISITION, nativeQuery = true)
        List<StockRequisitionEntity> getAll();

        @Transactional
        @Query(value =DbQuery.SEARCH_STOCK_REQUISITION_BY_REQUESTED_BY_QUERY, nativeQuery = true)
        List<StockRequisitionEntity> searchByRequestBy(String requestBy);

        @Transactional
        @Query(value =DbQuery.SEARCH_STOCK_REQUISITION_BY_STORE_ID_QUERY, nativeQuery = true)
        List<StockRequisitionEntity> searchByStoreId(int storeId);

        @Transactional
        @Query(value =DbQuery.SEARCH_STOCK_REQUISITION_BY_STORE_ID_AND_REQUESTED_BY_QUERY, nativeQuery = true)
        List<StockRequisitionEntity> searchByStoreIdAndRequestBy(int storeId, String requestBy);

        @Transactional
        @Query(value =DbQuery.GET_STOCK_REQS_BY_STATUS, nativeQuery = true)
        List<Object[]> getStockRequesisionByStatus();

        @Transactional
        @Query(value =DbQuery.GET_RE_ORDER_LEVELS, nativeQuery = true)
        List<Object[]> getReorderLevels();
}
