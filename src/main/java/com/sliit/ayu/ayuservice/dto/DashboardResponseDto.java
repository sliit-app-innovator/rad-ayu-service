package com.sliit.ayu.ayuservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class DashboardResponseDto {
    private  CardWidgetResDto stockRequsitions;
    private  CardWidgetResDto production;
    private  CardWidgetResDto reorderLevels;


    private List<MedicineStockAvailabilityResDTO> medicineStockChart;
    private List<Top5FastMovingItemsResDto> top5FastMovingMedicines;
    private List<Top5ExpiringItemsResDto> top5ExpiringMedicines;
    private List<MedicineMovementResDTO> medicineMovement;



}
