package com.sliit.ayu.ayuservice.repository;

import com.sliit.ayu.ayuservice.model.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<MedicineEntity, Integer> {

        MedicineEntity findByName(String name);

        @Transactional
        @Query(value =DbQuery.GET_ALL_MEDICINE, nativeQuery = true)
        List<MedicineEntity> getAll();

        @Transactional
        @Query(value =DbQuery.SEARCH_MEDICINE_BY_NAME_QUERY, nativeQuery = true)
        List<MedicineEntity> searchByName(String name);

        @Transactional
        @Query(value =DbQuery.SEARCH_MEDICINE_BY_NAME_OR_CODE_PAGING, nativeQuery = true)
        List<Object[]> searchPagination(int limit, int skip, String search);

        @Transactional
        @Query(value =DbQuery.SEARCH_MEDICINE_BY_NAME_OR_CODE_COUNT, nativeQuery = true)
        Integer searchPaginationCount(String search);

        @Transactional
        @Query(value =DbQuery.SEARCH_MEDICINE_STOCK_BY_STORE_MEDICINE_NAME_QUERY_COUNT, nativeQuery = true)
        Integer findAllByStoreIdAndNameCount(String search, int storeId);
}
