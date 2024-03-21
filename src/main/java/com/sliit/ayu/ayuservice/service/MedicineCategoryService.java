package com.sliit.ayu.ayuservice.service;

import com.sliit.ayu.ayuservice.dto.MedicineCategoryDTO;

import java.util.List;

public interface MedicineCategoryService {
    List<MedicineCategoryDTO> searchMedicineCategory(String name);
}
