package com.sliit.ayu.ayuservice.service.impl;

import com.sliit.ayu.ayuservice.dto.MedicineDTO;
import com.sliit.ayu.ayuservice.dto.UserDTO;
import com.sliit.ayu.ayuservice.service.MedicineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MedicineServiceImpl implements MedicineService {
    @Override
    public UserDTO addMedicine(MedicineDTO medicineDTO) {
        return null;
    }

    @Override
    public List<UserDTO> searchMedicine(String name) {
        return null;
    }

    @Override
    public UserDTO getMedicine(int id) {
        return null;
    }

    @Override
    public UserDTO updateMedicine(MedicineDTO medicineDTO) {
        return null;
    }

    @Override
    public void deleteMedicine(int id) {

    }
}
