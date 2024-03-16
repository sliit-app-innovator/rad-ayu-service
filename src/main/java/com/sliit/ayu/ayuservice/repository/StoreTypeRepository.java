package com.sliit.ayu.ayuservice.repository;

import com.sliit.ayu.ayuservice.model.StoreTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface StoreTypeRepository extends JpaRepository<StoreTypeEntity,Integer> {
    List<StoreTypeEntity> findByName(String name);


}
