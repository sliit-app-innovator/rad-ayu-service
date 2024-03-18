package com.sliit.ayu.ayuservice.service.impl;

import com.sliit.ayu.ayuservice.dto.StoreTypeDTO;
import com.sliit.ayu.ayuservice.model.StoreTypeEntity;
import com.sliit.ayu.ayuservice.repository.StoreTypeRepository;
import com.sliit.ayu.ayuservice.service.StoreTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StoreTypeServiceImpl implements StoreTypeService {

    @Autowired
    private StoreTypeRepository storeTypeRepository;
    @Override
    public List<StoreTypeDTO> searchStoreType(String name) {
        Stream<StoreTypeEntity> storeTypeStream;

        if (name == null || name.isEmpty()) {
            storeTypeStream = storeTypeRepository.findAll().stream();
        } else {
            storeTypeStream = storeTypeRepository.findByName(name).stream();
        }
        return storeTypeStream.map(StoreTypeEntity::toDTO).collect(Collectors.toList());
    }
}
