package com.sliit.ayu.ayuservice.service;

import com.sliit.ayu.ayuservice.dto.UnitDTO;

import java.util.List;

public interface UnitService {
    List<UnitDTO> searchUnit(String unit);
}
