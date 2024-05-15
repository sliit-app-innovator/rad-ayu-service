package com.sliit.ayu.ayuservice.service.impl;

import com.sliit.ayu.ayuservice.dto.StoreDTO;
import com.sliit.ayu.ayuservice.dto.UnitAddDTO;
import com.sliit.ayu.ayuservice.dto.UnitDTO;
import com.sliit.ayu.ayuservice.model.StoreTypeEntity;
import com.sliit.ayu.ayuservice.model.UnitEntity;
import com.sliit.ayu.ayuservice.repository.UnitRepository;
import com.sliit.ayu.ayuservice.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    private UnitRepository unitRepository;

    @Override
    public UnitAddDTO addUnit(UnitAddDTO unitAddDTO) {
    	unitAddDTO.setUpdatedDate(Calendar.getInstance().getTime());
    	unitAddDTO.setCreatedDate(Calendar.getInstance().getTime());
        unitRepository.save(unitAddDTO.toEntity());
        return null;
    }
    public List<UnitDTO> searchUnit(String unit) {
        Stream<UnitEntity> unitEntityStream;
        if (unit == null || unit.isEmpty()) {
            unitEntityStream = unitRepository.findAll().stream();
        } else {
            unitEntityStream = unitRepository.findByUnit(unit).stream();
        }
        return unitEntityStream.map(UnitEntity::toDTO).collect(Collectors.toList());

    }
}
