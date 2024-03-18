package com.sliit.ayu.ayuservice.service;

import com.sliit.ayu.ayuservice.dto.StoreDTO;

import java.util.List;

public interface StoreService {
    StoreDTO addStore(StoreDTO storeDTO);

    List<StoreDTO> searchStore(String name);

    StoreDTO getStore(int storeId);

    StoreDTO updateStore(StoreDTO storeDTO);

    void deleteStore(int storeId);
}
