package com.sliit.ayu.ayuservice.service.impl;

import com.sliit.ayu.ayuservice.constants.ErrorCode;
import com.sliit.ayu.ayuservice.dto.MedicineDTO;
import com.sliit.ayu.ayuservice.execption.AyuException;
import com.sliit.ayu.ayuservice.model.MedicineEntity;
import com.sliit.ayu.ayuservice.model.UserEntity;
import com.sliit.ayu.ayuservice.repository.MedicineRepository;
import com.sliit.ayu.ayuservice.service.MedicineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Slf4j
@Service
public class MedicineServiceImpl implements MedicineService {

    private MedicineRepository medicineRepository;

    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
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
        return null;
    }

    @Override
    public MedicineDTO getMedicine(int id) {
        return null;
    }

    @Override
    public MedicineDTO updateMedicine(MedicineDTO medicineDTO) {
        return null;
    }

    @Override
    public void deleteMedicine(int id) {

    }
}
