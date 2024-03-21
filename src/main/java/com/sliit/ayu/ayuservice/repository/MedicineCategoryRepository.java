package com.sliit.ayu.ayuservice.repository;

import com.sliit.ayu.ayuservice.model.MedicineCategoryEntity;
import com.sliit.ayu.ayuservice.model.StoreTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineCategoryRepository extends JpaRepository<MedicineCategoryEntity,Integer> {
    List<MedicineCategoryEntity> findByName(String name);
}
