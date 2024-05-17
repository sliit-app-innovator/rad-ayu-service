package com.sliit.ayu.ayuservice.service.impl;

import com.sliit.ayu.ayuservice.dto.*;
import com.sliit.ayu.ayuservice.repository.MedicineMovementRepository;
import com.sliit.ayu.ayuservice.repository.StockRequisitionRepository;
import com.sliit.ayu.ayuservice.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    private   MedicineMovementRepository medicineMovementRepository;

    @Autowired
    private StockRequisitionRepository stockRequisitionRepository;


    @Override
    public DashboardResponseDto getDashboardData(Integer medicineId) {
        DashboardResponseDto dashboardResponseDto = new DashboardResponseDto();

        List<MedicineStockAvailabilityResDTO> medicineChartData = getStockByItemWithStores(medicineId);
        dashboardResponseDto.setMedicineStockChart(medicineChartData);

        List<Top5FastMovingItemsResDto> top5FastMovingItems = getTop5FastMovingItems();
        dashboardResponseDto.setTop5FastMovingMedicines(top5FastMovingItems);

        List<Top5ExpiringItemsResDto> top5ExpiringItems = getTop5ExpiringItems();
        dashboardResponseDto.setTop5ExpiringMedicines(top5ExpiringItems);

        List<MedicineMovementResDTO> medicineMovementResDTO = getMedicineMovement(medicineId);
        dashboardResponseDto.setMedicineMovement(medicineMovementResDTO);

        CardWidgetResDto cardWidgetResDto = getStockRequsistions();
        int total = cardWidgetResDto.getTotal();
        int open = cardWidgetResDto.getPending();
        int completed = total - open;
        double percentage = (completed * 100.0) / total;
        if(total == 0){
            percentage = 100;
        }
        dashboardResponseDto.setStockRequsitions(new CardWidgetResDto(total, open, completed, percentage));

        CardWidgetResDto reorderLevels = getReorderLevels();
        dashboardResponseDto.setReorderLevels(reorderLevels);


        return  dashboardResponseDto;
    }

    private List<Top5ExpiringItemsResDto> getTop5ExpiringItems() {
        List<Object[]> results = medicineMovementRepository.getTop5ExpiringItems();
        return   results.stream().map(result -> new Top5ExpiringItemsResDto(
                result[0].toString(),
                Integer.parseInt(result[2].toString()),
                result[3].toString(),
                Integer.parseInt(result[4].toString()),
                result[5].toString()
        )).collect(Collectors.toList());
    }

    private CardWidgetResDto getStockRequsistions() {
        List<Object[]> results =  stockRequisitionRepository.getStockRequesisionByStatus();
        return results.stream().map(result -> new CardWidgetResDto(
                Integer.parseInt(result[0].toString()),
                Integer.parseInt(result[1].toString()),
                0,
                0.00
        )).findFirst().orElse(null);
    }

    private CardWidgetResDto getReorderLevels() {
        List<Object[]> results =  stockRequisitionRepository.getReorderLevels();
        return results.stream().map(result -> new CardWidgetResDto(
                Integer.parseInt(result[1].toString()),
                Integer.parseInt(result[0].toString()),
                0,
                0.00
        )).findFirst().orElse(null);
    }



    private List<MedicineMovementResDTO> getMedicineMovement(Integer medicineId) {
        List<Object[]> results = medicineMovementRepository.getMedicineMovement(medicineId);
        return   results.stream().map(result -> new MedicineMovementResDTO(
                Integer.parseInt(result[0].toString()),
                result[1].toString(),
                Integer.parseInt(result[2].toString()),
                Integer.parseInt(result[3].toString())
        )).collect(Collectors.toList());
    }

    private List<Top5FastMovingItemsResDto> getTop5FastMovingItems() {
        List<Object[]> results = medicineMovementRepository.getTop5FastMovingItems();
        return   results.stream().map(result -> new Top5FastMovingItemsResDto(
                Integer.parseInt(result[0].toString()),
                result[1].toString(),
                Integer.parseInt(result[2].toString())
        )).collect(Collectors.toList());
    }



    private List<MedicineStockAvailabilityResDTO> getStockByItemWithStores(Integer medicineId) {
        List<Object[]> results = medicineMovementRepository.getStockByItemWithStores(medicineId);
        List<MedicineStockAvailabilityResDTO> MedicineStockChart =  results.stream().map(result -> new MedicineStockAvailabilityResDTO(
                Integer.parseInt(result[0].toString()),
                result[1].toString(),
                Integer.parseInt(result[2].toString()),
                0
        )).collect(Collectors.toList());

        // calculate sum of stock
        int totalStock = MedicineStockChart.stream().mapToInt(MedicineStockAvailabilityResDTO::getAvailableStock).sum();
        double percentageFor100 = 100.0 / totalStock;
        MedicineStockChart.forEach(medicineStockAvailabilityResDTO -> {
            medicineStockAvailabilityResDTO.setPercentage((medicineStockAvailabilityResDTO.getAvailableStock() * percentageFor100));
        });
        return MedicineStockChart;
    }
}
