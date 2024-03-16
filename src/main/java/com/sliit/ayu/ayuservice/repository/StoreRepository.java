package com.sliit.ayu.ayuservice.repository;

import com.sliit.ayu.ayuservice.model.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StoreRepository   extends JpaRepository<StoreEntity,Integer> {


    @Transactional
    @Query(value =DbQuery.SEARCH_STORE_BY_NAME_QUERY, nativeQuery = true)
    List<StoreEntity> findByName(String name);
}
