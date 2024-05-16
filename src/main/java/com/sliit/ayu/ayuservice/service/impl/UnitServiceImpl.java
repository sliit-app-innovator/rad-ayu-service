package com.sliit.ayu.ayuservice.service.impl;

import com.sliit.ayu.ayuservice.dto.UnitDTO;
import com.sliit.ayu.ayuservice.execption.AyuException;
import com.sliit.ayu.ayuservice.model.UnitEntity;
import com.sliit.ayu.ayuservice.repository.UnitRepository;
import com.sliit.ayu.ayuservice.service.UnitService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



@Service
public class UnitServiceImpl implements UnitService {

    public static final String AU_003 = "AU003";
    public static final String UNIT_CANNOT_BE_FOUND = "Unit cannot be found";

    @Autowired
    private UnitRepository unitRepository;

    @Override
    public UnitDTO addUnit(UnitDTO unitAddDTO) {
    	unitAddDTO.setUpdatedDate(Calendar.getInstance().getTime());
    	unitAddDTO.setCreatedDate(Calendar.getInstance().getTime());
      return  unitRepository.save(unitAddDTO.toEntity()).toDTO();
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

    @Override
    public UnitDTO updateUnit(UnitDTO unitDTO) {
        UnitEntity unitEntity = unitRepository.findById(unitDTO.getId())
                .orElseThrow(() -> AyuException.builder().errorCode(AU_003).errorMessage(UNIT_CANNOT_BE_FOUND).build());
            unitEntity.setUnit(unitDTO.getUnit());
            unitEntity.setCode(unitDTO.getCode());
            unitEntity.setUpdatedDate(Calendar.getInstance().getTime());
        return unitRepository.save(unitEntity).toDTO();

    }

    @Override
    public UnitDTO getUnit(int id) {
        return unitRepository.findById(id)
                .map(UnitEntity::toDTO)
                .orElseThrow(() -> {
                    return new EntityNotFoundException("Unit not found with id: " + id);
                });

    }

    @Override
    public void deleteUnit(int id) {
        unitRepository.findById(id).ifPresentOrElse(
                unitRepository::delete,
                () -> {
                    throw AyuException.builder().errorCode(AU_003).errorMessage(UNIT_CANNOT_BE_FOUND).build();
                }
        );
    }
}
