package com.sliit.ayu.ayuservice.service.impl;

import com.sliit.ayu.ayuservice.dto.UserDTO;
import com.sliit.ayu.ayuservice.dto.WardDTO;
import com.sliit.ayu.ayuservice.dto.WardUpdateDTO;
import com.sliit.ayu.ayuservice.execption.AyuException;
import com.sliit.ayu.ayuservice.model.UserEntity;
import com.sliit.ayu.ayuservice.model.WardEntity;
import com.sliit.ayu.ayuservice.repository.UserRepository;
import com.sliit.ayu.ayuservice.repository.WardRepository;
import com.sliit.ayu.ayuservice.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WardServiceImpl implements WardService {

    public static final String AU_001 = "AU001";
    public static final String WARD_CANNOT_BE_FOUND = "Ward cannot be found";
    @Autowired
    private WardRepository wardRepository;
    @Override
    public WardDTO addWard(WardDTO wardDTO) {
        wardDTO.setCreatedDate(Calendar.getInstance().getTime());
        wardDTO.setUpdatedDate(Calendar.getInstance().getTime());
        wardRepository.save(wardDTO.toEntity());
        WardEntity wardEntity = wardRepository.findByWardNumber(wardDTO.getWardNumber());
        return wardEntity.toDTO();
    }

    @Override
    public List<WardDTO> searchWard(int typeId) {
        List<WardDTO> entityList = new ArrayList<>();

        if (typeId == 0) {
            wardRepository.findAll().forEach(wardEntity -> entityList.add(wardEntity.toDTO()));
        } else {
            wardRepository.findByWardType(typeId).forEach(wardEntity -> entityList.add(wardEntity.toDTO()));
        }
        return entityList;
    }

    @Override
    public WardDTO getWard(int id) {
        Optional<WardEntity> optional = wardRepository.findById(id);
        if(optional.isPresent()){
            return optional.get().toDTO();
        } else {
            throw AyuException.builder().errorCode(AU_001).errorMessage(WARD_CANNOT_BE_FOUND).build();
        }
    }

    @Override
    public void deleteWard(int id) {
        Optional<WardEntity> optional = wardRepository.findById(id);
        if(optional.isPresent()){
            wardRepository.delete(optional.get());
        } else {
            throw AyuException.builder().errorCode("AYU001").errorMessage(WARD_CANNOT_BE_FOUND).build();
        }
    }

    @Override
    public WardDTO updateWard(WardUpdateDTO wardDTO) {
        Optional<WardEntity> optional = wardRepository.findById(wardDTO.getId());
        if (optional.isPresent()) {
            WardEntity wardEntity = optional.get();
            if (wardDTO.getWardNumber() != null && wardDTO.getWardNumber() != 0) {
                wardEntity.setWardNumber(wardDTO.getWardNumber());
            }
            if (wardDTO.getDescription() != null && !wardDTO.getDescription().isEmpty()) {
                wardEntity.setDescription(wardDTO.getDescription());
            }
            if (wardDTO.getTypeId() != null && wardDTO.getTypeId() != 0) {
                wardEntity.setTypeId(wardDTO.getTypeId());
            }
            if (wardDTO.getCapacity() != null && wardDTO.getCapacity() != 0) {
                wardEntity.setCapacity(wardDTO.getCapacity());
            }
            wardEntity.setUpdatedDate(new Date());

            wardRepository.save(wardEntity);
            optional = wardRepository.findById(wardDTO.getId());
            if (optional.isPresent()) {
                return optional.get().toDTO();
            } else {
                throw AyuException.builder().errorCode(AU_001).errorMessage(WARD_CANNOT_BE_FOUND).build();
            }
        } else {
            throw AyuException.builder().errorCode(AU_001).errorMessage(WARD_CANNOT_BE_FOUND).build();
        }
    }
}
