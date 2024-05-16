package com.sliit.ayu.ayuservice.repository;

import com.sliit.ayu.ayuservice.dto.LotsResponseDto;
import com.sliit.ayu.ayuservice.model.MedicineMovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MedicineMovementRepository extends JpaRepository<MedicineMovementEntity, Integer> {

    @Transactional
    @Query(value =DbQuery.SEARCH_LOTS_BY_STORE_MEDICINE_QUERY, nativeQuery = true)
    List<Object[]> findAllByStoreIdAndMedicineId(int storeId, int medicineId);

    @Transactional
    @Query(value =DbQuery.SEARCH_MEDICINE_STOCK_BY_STORE_MEDICINE_NAME_QUERY, nativeQuery = true)
    List<Object[]> findAllByStoreIdAndName(int storeId, String search , int limit, int skip);

    @Transactional
    @Query(value =DbQuery.GET_MEDICINE_STOCK_BY_MEDICINE_ID_WITH_STORE, nativeQuery = true)
    List<Object[]> getStockByItemWithStores(Integer medicineId);

    @Transactional
    @Query(value =DbQuery.GET_TOP_5_FAST_MOVING_MEDICINCES, nativeQuery = true)
    List<Object[]> getTop5FastMovingItems();


    @Transactional
    @Query(value =DbQuery.GET_MEDICINE_MOVEMENT, nativeQuery = true)
    List<Object[]> getMedicineMovement(Integer medicineId);

    @Transactional
    @Query(value =DbQuery.GET_TOP_5_EXPIRING_MEDICINES, nativeQuery = true)
    List<Object[]> getTop5ExpiringItems();
}
