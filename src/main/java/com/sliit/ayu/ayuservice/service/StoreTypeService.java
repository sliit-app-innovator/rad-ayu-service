package com.sliit.ayu.ayuservice.service;

import com.sliit.ayu.ayuservice.dto.StoreTypeDTO;
import com.sliit.ayu.ayuservice.dto.UserDTO;

import java.util.List;

public interface StoreTypeService {
    List<StoreTypeDTO> searchStoreType(String name);
}
