package com.sliit.ayu.ayuservice.service;


import com.sliit.ayu.ayuservice.dto.StoreDTO;
import com.sliit.ayu.ayuservice.dto.UnitDTO;

import jakarta.validation.Valid;

import java.util.List;

public interface UnitService {
    List<UnitDTO> searchUnit(String unit);
   
    public UnitDTO addUnit(@Valid UnitDTO unitAddDTO);

    UnitDTO updateUnit(UnitDTO unitDTO);
}
