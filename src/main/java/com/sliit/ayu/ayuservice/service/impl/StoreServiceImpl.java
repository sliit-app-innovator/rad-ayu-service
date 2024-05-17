package com.sliit.ayu.ayuservice.service.impl;

import com.sliit.ayu.ayuservice.dto.StoreDTO;
import com.sliit.ayu.ayuservice.execption.AyuException;
import com.sliit.ayu.ayuservice.model.StoreEntity;
import com.sliit.ayu.ayuservice.repository.StoreRepository;
import com.sliit.ayu.ayuservice.service.StoreService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {

    public static final String AU_003 = "AU003";
    public static final String STORE_CANNOT_BE_FOUND = "Store cannot be found";
    @Autowired
    private StoreRepository storeRepository;
    @Override
    public StoreDTO addStore(StoreDTO storeDTO) {
        storeDTO.setUpdatedDate(Calendar.getInstance().getTime());
        storeDTO.setCreatedDate(Calendar.getInstance().getTime());
        return storeRepository.save(storeDTO.toEntity()).toDTO();
    }

    @Override
    public List<StoreDTO> searchStore(String name) {
        List<StoreEntity> stores;
        if (name == null || name.trim().isEmpty()) {
            stores = storeRepository.findAll();
        } else {
            stores = storeRepository.findByName(name);
        }
        return stores.stream()
                .map(StoreEntity::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StoreDTO getStore(int storeId) {
        return storeRepository.findById(storeId)
                .map(StoreEntity::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with id: " + storeId));
    }

    @Override
    public StoreDTO updateStore(StoreDTO storeDTO) {
        StoreEntity storeEntity = storeRepository.findById(storeDTO.getId())
                .orElseThrow(() -> AyuException.builder().errorCode(AU_003).errorMessage(STORE_CANNOT_BE_FOUND).build());
        if (StringUtils.hasText(storeDTO.getName())) {
            storeEntity.setName(storeDTO.getName());
        }
        storeEntity.setType(storeDTO.getType());
        return storeRepository.saveAndFlush(storeEntity).toDTO();
    }

    @Override
    public void deleteStore(int storeId) {
        storeRepository.findById(storeId).ifPresentOrElse(
                storeRepository::delete,
                () -> { throw AyuException.builder().errorCode(AU_003).errorMessage(STORE_CANNOT_BE_FOUND).build(); }
        );
    }
}
