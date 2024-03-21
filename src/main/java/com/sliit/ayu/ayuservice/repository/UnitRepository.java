package com.sliit.ayu.ayuservice.repository;

import com.sliit.ayu.ayuservice.model.StoreTypeEntity;
import com.sliit.ayu.ayuservice.model.UnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnitRepository extends JpaRepository<UnitEntity,Integer> {

    List<UnitEntity> findByUnit(String unit);
}
