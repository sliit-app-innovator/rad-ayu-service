package com.sliit.ayu.ayuservice.repository;

import com.sliit.ayu.ayuservice.model.MedicineMovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineMovementRepository extends JpaRepository<MedicineMovementEntity, Integer> {
}
