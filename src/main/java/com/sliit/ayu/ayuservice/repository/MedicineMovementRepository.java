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
}
