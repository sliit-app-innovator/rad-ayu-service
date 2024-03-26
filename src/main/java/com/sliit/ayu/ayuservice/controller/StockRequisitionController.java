package com.sliit.ayu.ayuservice.controller;

import com.sliit.ayu.ayuservice.constants.Constants;
import com.sliit.ayu.ayuservice.dto.*;
import com.sliit.ayu.ayuservice.service.StockRequisitionService;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ayu/service/v1")
@Slf4j
@Validated
public class StockRequisitionController {

    public static final String STORE_ID = "store-id";
    @Autowired
    private StockRequisitionService stockRequisitionService;


    @PostMapping("/stock-requisition")
    public ResponseEntity<StockRequisitionDTO> addStockRequisition(@Valid @RequestBody StockRequisitionDTO stockRequisitionDTO, @RequestHeader Map<String, String> headers){
        log.info("User creation request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.CREATED).body(stockRequisitionService.requestStockRequisition(stockRequisitionDTO));
    }

    @GetMapping("/stock-requisition")
    public ResponseEntity<List<StockRequisitionDTO>> searchStockRequisition(@RequestHeader Map<String, String> headers){
        log.info("User search request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        int storeId = headers.get(STORE_ID) != null ? Integer.parseInt(headers.get(STORE_ID)) : 0;
        return ResponseEntity.status(HttpStatus.OK).body(stockRequisitionService.searchStockRequisitionRequest(storeId, headers.get("requested-by")));
    }

    @GetMapping("/stock-requisition/{id}")
    public ResponseEntity<StockRequisitionDTO> getStockRequisition(@PathVariable int id, @RequestHeader Map<String, String> headers){
        log.info("User get request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.OK).body(stockRequisitionService.getStockRequisitionRequest(id));
    }

    @PutMapping("/stock-requisition/{id}")
    public ResponseEntity<StockRequisitionDTO> updateStockRequisition(@PathVariable int id, @RequestBody StockRequisitionDTO stockRequisitionDTO, @RequestHeader Map<String, String> headers){
        log.info("User update request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        stockRequisitionDTO.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(stockRequisitionService.updateStockRequisitionRequest(stockRequisitionDTO));
    }

    @DeleteMapping("/stock-requisition/{id}")
    public ResponseEntity<Void> deleteStockRequisition(@PathVariable int id, @RequestHeader Map<String, String> headers){
        log.info("User delete request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        stockRequisitionService.deleteStockRequisitionRequest(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
