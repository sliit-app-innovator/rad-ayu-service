package com.sliit.ayu.ayuservice.service.impl;

import com.sliit.ayu.ayuservice.constants.ErrorCode;
import com.sliit.ayu.ayuservice.dto.MedicineDTO;
import com.sliit.ayu.ayuservice.dto.MedicineTypeDTO;
import com.sliit.ayu.ayuservice.dto.MedicineUpdateDTO;
import com.sliit.ayu.ayuservice.execption.AyuException;
import com.sliit.ayu.ayuservice.model.MedicineEntity;
import com.sliit.ayu.ayuservice.model.MedicineTypeEntity;
import com.sliit.ayu.ayuservice.repository.MedicineRepository;
import com.sliit.ayu.ayuservice.repository.MedicineTypeRepository;
import com.sliit.ayu.ayuservice.service.MedicineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final MedicineTypeRepository medicineTypeRepository;

    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository, MedicineTypeRepository medicineTypeRepository) {
        this.medicineRepository = medicineRepository;
        this.medicineTypeRepository = medicineTypeRepository;
    }

    @Override
    public MedicineDTO addMedicine(MedicineDTO medicineDTO) {
        MedicineEntity medicineEntity = medicineRepository.findByName(medicineDTO.getName());
        if (medicineEntity != null) {
            throw AyuException.builder().errorCode(ErrorCode.AU_002.getCode()).errorMessage(ErrorCode.AU_002.getMessage()).build();
        } else {
            medicineDTO.setCreatedDate(Calendar.getInstance().getTime());
            medicineDTO.setUpdatedDate(Calendar.getInstance().getTime());
            medicineRepository.save(medicineDTO.toEntity());
            medicineEntity = medicineRepository.findByName(medicineDTO.getName());
            return medicineEntity.toDTO();
        }
    }

    @Override
    public List<MedicineDTO> searchMedicine(String name) {
        List<MedicineDTO> entityList = new ArrayList<>();
        if (name == null || name.isEmpty()) {
            medicineRepository.findAll().forEach(medicineEntity -> entityList.add(medicineEntity.toDTO()));
        } else {
            medicineRepository.searchByName(name).forEach(medicineEntity -> entityList.add(medicineEntity.toDTO()));
        }
        return entityList;
    }

    @Override
    public MedicineDTO getMedicine(int id) {
        Optional<MedicineEntity> optional = medicineRepository.findById(id);
        if(optional.isPresent()){
            return optional.get().toDTO();
        } else {
            throw AyuException.builder().errorCode(ErrorCode.AU_001.getCode()).errorMessage(ErrorCode.AU_001.getMessage()).build();
        }
    }

    @Override
    public MedicineDTO updateMedicine(MedicineUpdateDTO medicineDTO) {
        Optional<MedicineEntity> optional = medicineRepository.findById(medicineDTO.getId());
        if (optional.isPresent()) {
            MedicineEntity medicineEntity = optional.get();
            if (medicineDTO.getName() != null && !medicineDTO.getName().isEmpty()) {
                medicineEntity.setName(medicineDTO.getName());
            }
            if (medicineDTO.getCode() != null && !medicineDTO.getCode().isEmpty()) {
                medicineEntity.setCode(medicineDTO.getCode());
            }
            medicineEntity.setType(medicineDTO.getType());

            if (medicineDTO.getMedicineType() != null && medicineDTO.getMedicineType() != 0) {
                medicineEntity.setMedicineType(medicineDTO.getMedicineType());
            }
            if (medicineDTO.getUnit() != null && medicineDTO.getUnit() != 0) {
                medicineEntity.setUnit(medicineDTO.getUnit());
            }

            medicineEntity.setUpdatedDate(new Date());
            medicineRepository.save(medicineEntity);
            optional = medicineRepository.findById(medicineDTO.getId());
            if (optional.isPresent()) {
                return optional.get().toDTO();
            } else {
                throw AyuException.builder().errorCode(ErrorCode.AU_001.getCode()).errorMessage(ErrorCode.AU_001.getMessage()).build();
            }
        } else {
            throw AyuException.builder().errorCode(ErrorCode.AU_001.getCode()).errorMessage(ErrorCode.AU_001.getMessage()).build();
        }
    }

    @Override
    public void deleteMedicine(int id) {
        Optional<MedicineEntity> optional = medicineRepository.findById(id);
        if(optional.isPresent()){
            medicineRepository.delete(optional.get());
        } else {
            throw AyuException.builder().errorCode(ErrorCode.AU_001.getCode()).errorMessage(ErrorCode.AU_001.getMessage()).build();
        }
    }

    @Override
    public MedicineTypeDTO addMedicineType(MedicineTypeDTO medicineTypeDTO) {
        MedicineTypeEntity entity = medicineTypeRepository.findByName(medicineTypeDTO.getName());
        if (entity != null) {
            throw AyuException.builder().errorCode(ErrorCode.AU_002.getCode()).errorMessage(ErrorCode.AU_002.getMessage()).build();
        } else {
            medicineTypeDTO.setCreatedDate(Calendar.getInstance().getTime());
            medicineTypeDTO.setUpdatedDate(Calendar.getInstance().getTime());
            medicineTypeRepository.save(medicineTypeDTO.toEntity());
            entity = medicineTypeRepository.findByName(medicineTypeDTO.getName());
            return entity.toDTO();
        }
    }

    @Override
    public List<MedicineTypeDTO> searchMedicineType(String name) {
        List<MedicineTypeDTO> entityList = new ArrayList<>();
        if (name == null || name.isEmpty()) {
            medicineTypeRepository.findAll().forEach(medicineEntity -> entityList.add(medicineEntity.toDTO()));
        } else {
            medicineTypeRepository.searchByName(name).forEach(medicineEntity -> entityList.add(medicineEntity.toDTO()));
        }
        return entityList;
    }

    @Override
    public MedicineTypeDTO getMedicineType(int id) {
        Optional<MedicineTypeEntity> optional = medicineTypeRepository.findById(id);
        if(optional.isPresent()){
            return optional.get().toDTO();
        } else {
            throw AyuException.builder().errorCode(ErrorCode.AU_001.getCode()).errorMessage(ErrorCode.AU_001.getMessage()).build();
        }
    }

    @Override
    public MedicineTypeDTO updateMedicineType(MedicineTypeDTO medicineTypeDTO) {
        Optional<MedicineTypeEntity> optional = medicineTypeRepository.findById(medicineTypeDTO.getId());
        if (optional.isPresent()) {
            MedicineTypeEntity medicineTypeEntity = optional.get();
            medicineTypeEntity.setName(medicineTypeDTO.getName());
            medicineTypeEntity.setUpdatedDate(new Date());
            medicineTypeRepository.save(medicineTypeEntity);
            optional = medicineTypeRepository.findById(medicineTypeDTO.getId());
            if (optional.isPresent()) {
                return optional.get().toDTO();
            } else {
                throw AyuException.builder().errorCode(ErrorCode.AU_001.getCode()).errorMessage(ErrorCode.AU_001.getMessage()).build();
            }
        } else {
            throw AyuException.builder().errorCode(ErrorCode.AU_001.getCode()).errorMessage(ErrorCode.AU_001.getMessage()).build();
        }
    }

    @Override
    public void deleteMedicineType(int id) {
        Optional<MedicineTypeEntity> optional = medicineTypeRepository.findById(id);
        if(optional.isPresent()){
            medicineTypeRepository.delete(optional.get());
        } else {
            throw AyuException.builder().errorCode(ErrorCode.AU_001.getCode()).errorMessage(ErrorCode.AU_001.getMessage()).build();
        }
    }
}
