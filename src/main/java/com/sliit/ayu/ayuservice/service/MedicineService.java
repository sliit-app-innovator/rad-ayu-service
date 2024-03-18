package com.sliit.ayu.ayuservice.service;

import com.sliit.ayu.ayuservice.dto.MedicineDTO;
import com.sliit.ayu.ayuservice.dto.UserDTO;

import java.util.List;

public interface MedicineService {
    public MedicineDTO addMedicine(MedicineDTO medicineDTO);
    public List<MedicineDTO> searchMedicine(String name);
    public MedicineDTO getMedicine(int id);
    public MedicineDTO updateMedicine(MedicineDTO medicineDTO);
    public void deleteMedicine(int id);
}
