package com.sliit.ayu.ayuservice.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class StockMedicineIssueRequestDTO {
    private Integer id;
    private Integer issueType;
    private Date date;
    private String description;
    private String  doctor;
    private String patient;
    private Integer  storeId;
    private Date createdDate;
    private Date updatedDate;

    private List<StockMedicinesRequestDTO> medicineList;
}

