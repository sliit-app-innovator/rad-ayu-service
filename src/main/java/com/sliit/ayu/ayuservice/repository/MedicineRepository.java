package com.sliit.ayu.ayuservice.repository;

import com.sliit.ayu.ayuservice.model.MedicineEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MedicineRepository extends CrudRepository<MedicineEntity, Integer>, JpaSpecificationExecutor<MedicineEntity>  {

        MedicineEntity findByName(String name);

        @Transactional
        @Query(value =DbQuery.GET_ALL_MEDICINE, nativeQuery = true)
        List<MedicineEntity> getAll();

        @Transactional
        @Query(value =DbQuery.SEARCH_MEDICINE_BY_NAME_QUERY, nativeQuery = true)
        List<MedicineEntity> searchByName(String name);
}