package com.sliit.ayu.ayuservice.service.impl;

import com.sliit.ayu.ayuservice.constants.ErrorCode;
import com.sliit.ayu.ayuservice.dto.*;
import com.sliit.ayu.ayuservice.execption.AyuException;
import com.sliit.ayu.ayuservice.model.MedicineEntity;
import com.sliit.ayu.ayuservice.model.MedicineTypeEntity;
import com.sliit.ayu.ayuservice.repository.MedicineRepository;
import com.sliit.ayu.ayuservice.repository.MedicineTypeRepository;
import com.sliit.ayu.ayuservice.service.MedicineService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Internal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.*;

@Slf4j
@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private ModelMapper modelMapper;
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

            medicineEntity.setReorderLevel(medicineDTO.getReorderLevel());
            medicineEntity.setIsExpire(medicineDTO.getIsExpire());

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

    @Override
    public MedicineSearchResponseDTO searchPagination(int page, int perPage, String search) {
        int limit = perPage;
        int skip = (page - 1) * perPage;
        List<Object[]>  medicineEntityList= medicineRepository.searchPagination(limit,skip,search);
        List<MedicineDTO> dtos = medicineEntityList.stream().map(row -> new MedicineDTO(
                Optional.ofNullable(row[0]).map(Integer.class::cast).orElse(null),
                Optional.ofNullable(row[1]).map(String.class::cast).orElse(null),
                Optional.ofNullable(row[2]).map(String.class::cast).orElse(null),
                Optional.ofNullable(row[3]).map(Integer.class::cast).orElse(null),
                Optional.ofNullable(row[4]).map(Integer.class::cast).orElse(null),
                Optional.ofNullable(row[5]).map(Integer.class::cast).orElse(null),
                Optional.ofNullable(row[6]).map(String.class::cast).orElse(null),
                (Date) row[7], // Assuming date fields are always non-null
                (Date) row[8],
                Optional.ofNullable(row[9]).map(Boolean.class::cast).orElse(null),
                Optional.ofNullable(row[10]).map(Integer.class::cast).orElse(null)
        )).collect(Collectors.toList());

        MedicineSearchResponseDTO responseDTO = new MedicineSearchResponseDTO();
        responseDTO.setData(dtos);
        responseDTO.setPerPage(perPage);
        responseDTO.setPage(page);
        responseDTO.setTotal(medicineRepository.searchPaginationCount(search));
        responseDTO.setTotalPages(responseDTO.getTotal()/perPage);

        return  responseDTO;
    }
}
