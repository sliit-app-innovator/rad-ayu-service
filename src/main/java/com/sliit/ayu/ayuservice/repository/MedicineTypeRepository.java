package com.sliit.ayu.ayuservice.repository;

import com.sliit.ayu.ayuservice.model.MedicineTypeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MedicineTypeRepository extends CrudRepository<MedicineTypeEntity, Integer>, JpaSpecificationExecutor<MedicineTypeEntity>  {

        MedicineTypeEntity findByName(String name);

        @Transactional
        @Query(value =DbQuery.GET_ALL_MEDICINE_TYPE, nativeQuery = true)
        List<MedicineTypeEntity> getAll();

        @Transactional
        @Query(value =DbQuery.SEARCH_MEDICINE_TYPE_BY_NAME_QUERY, nativeQuery = true)
        List<MedicineTypeEntity> searchByName(String name);
}
