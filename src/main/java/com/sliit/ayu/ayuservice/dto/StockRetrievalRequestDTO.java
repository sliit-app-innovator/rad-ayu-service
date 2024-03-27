package com.sliit.ayu.ayuservice.dto;

import jakarta.validation.Valid;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Valid
public class StockRetrievalRequestDTO {
    private Integer id;
    private Date date;
    private String  source;
    private Integer  storeId;
    private String requestBy;
    private String approvedBy;
    private String reference;
    private String description;
    private Date createdDate;
    private Date updatedDate;
    private Integer medicineId;

    private List<StockMedicineRequestDTO> medicineList;

    public Integer getMedicineId(StockMedicineRequestDTO stockMedicineRequestDTO) {
        return medicineId;
    }
}
