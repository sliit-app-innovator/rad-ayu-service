package com.sliit.ayu.ayuservice.service;

import com.sliit.ayu.ayuservice.dto.*;

import java.util.List;

public interface MedicineService {
    public MedicineDTO addMedicine(MedicineDTO medicineDTO);
    public List<MedicineDTO> searchMedicine(String name);
    public MedicineDTO getMedicine(int id);
    public MedicineDTO updateMedicine(MedicineUpdateDTO medicineDTO);
    public void deleteMedicine(int id);

    public MedicineTypeDTO addMedicineType(MedicineTypeDTO medicineTypeDTO);
    public List<MedicineTypeDTO> searchMedicineType(String name);
    public MedicineTypeDTO getMedicineType(int id);
    public MedicineTypeDTO updateMedicineType(MedicineTypeDTO medicineTypeDTO);
    public void deleteMedicineType(int id);

    MedicineSearchResponseDTO searchPagination(int page, int perPage, String search);
}
