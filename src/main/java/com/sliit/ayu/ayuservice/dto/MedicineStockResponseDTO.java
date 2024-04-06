package com.sliit.ayu.ayuservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class MedicineStockResponseDTO {
    private Integer total;
    private Integer page;
    private Integer perPage;
    private Integer totalPages;
    private List<MedicineStockDTO> data;
}
