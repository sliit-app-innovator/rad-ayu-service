package com.sliit.ayu.ayuservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class MedicineSearchResponseDTO {
    private Integer total;
    private Integer page;
    private Integer perPage;
    private Integer totalPages;
    private List<MedicineDTO> data;
}
