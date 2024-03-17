package com.sliit.ayu.ayuservice.service;

import com.sliit.ayu.ayuservice.dto.MedicineDTO;
import com.sliit.ayu.ayuservice.dto.UserDTO;

import java.util.List;

public interface MedicineService {
    public UserDTO addMedicine(MedicineDTO medicineDTO);
    public List<UserDTO> searchMedicine(String name);
    public UserDTO getMedicine(int id);
    public UserDTO updateMedicine(MedicineDTO medicineDTO);
    public void deleteMedicine(int id);
}
