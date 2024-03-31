package com.sliit.ayu.ayuservice.repository;

import com.sliit.ayu.ayuservice.model.UserEntity;
import com.sliit.ayu.ayuservice.model.WardEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WardRepository extends CrudRepository<WardEntity, Integer>, JpaSpecificationExecutor<WardEntity> {

    @Transactional
    @Query(value =DbQuery.SEARCH_WARD_BY_WARD_NUMBER_QUERY, nativeQuery = true)
    WardEntity findByWardNumber(int wardNumber);

    @Transactional
    @Query(value =DbQuery.SEARCH_WARD_BY_WARD_TYPE_QUERY, nativeQuery = true)
    List<WardEntity> findByWardType(int typeId);

    @Transactional
    @Query(value =DbQuery.SEARCH_WARD_BY_DESCRIPTION_QUERY, nativeQuery = true)
    List<WardEntity> findByWardDescription(String description);
}
