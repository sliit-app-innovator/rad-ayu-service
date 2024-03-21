package com.sliit.ayu.ayuservice.service.impl;

import com.sliit.ayu.ayuservice.dto.MedicineCategoryDTO;
import com.sliit.ayu.ayuservice.model.MedicineCategoryEntity;
import com.sliit.ayu.ayuservice.model.StoreTypeEntity;
import com.sliit.ayu.ayuservice.repository.MedicineCategoryRepository;
import com.sliit.ayu.ayuservice.service.MedicineCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MedicineCategoryServiceImpl  implements MedicineCategoryService {

    @Autowired
    private MedicineCategoryRepository medicineCategoryRepository;

    @Override
    public List<MedicineCategoryDTO> searchMedicineCategory(String name) {

        Stream<MedicineCategoryEntity> medicineCategoryStream;

        if (name == null || name.isEmpty()) {
            medicineCategoryStream = medicineCategoryRepository.findAll().stream();
        } else {
            medicineCategoryStream = medicineCategoryRepository.findByName(name).stream();
        }
        return medicineCategoryStream.map(MedicineCategoryEntity::toDTO).collect(Collectors.toList());

    }
}
