package com.sliit.ayu.ayuservice.service;

import com.sliit.ayu.ayuservice.dto.MedicineDTO;
import com.sliit.ayu.ayuservice.dto.StockMedicineExpireDatesRequestDTO;
import com.sliit.ayu.ayuservice.dto.StockMedicineRequestDTO;
import com.sliit.ayu.ayuservice.dto.StockRetrievalRequestDTO;
import com.sliit.ayu.ayuservice.model.StockRetrievalEntity;
import com.sliit.ayu.ayuservice.repository.MedicineIssueRepository;
import com.sliit.ayu.ayuservice.repository.MedicineLotRepository;
import com.sliit.ayu.ayuservice.repository.MedicineMovementRepository;
import com.sliit.ayu.ayuservice.repository.StockRetrievalRepository;
import com.sliit.ayu.ayuservice.service.impl.StockServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class StockServiceImplTest {

    @Autowired
    private ModelMapper modelMapper;
    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private MedicineIssueRepository medicineIssueRepository;
    @Mock
    private MedicineMovementRepository medicineMovementRepository;
    @Mock
    private MedicineLotRepository medicineLotRepository;
    @Mock
    private StockRetrievalRepository stockRetrievalRepository ;



    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void stockRetrieval() {
        // Arrange

    }


    @Test
    void getLotsByStoreAndMedicine() {
    }

    @Test
    void stockMedicineIssue() {
    }

    @Test
    void getMedicinesWithStock() {
    }
}