package com.sliit.ayu.ayuservice.controller;
import com.sliit.ayu.ayuservice.constants.Constants;
import com.sliit.ayu.ayuservice.dto.*;
import com.sliit.ayu.ayuservice.service.StockService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ayu/service/v1/stock")
@Slf4j
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/stock-receive")
    public ResponseEntity<StockRetrievalResponseDTO> stockRetrieval(@Valid @RequestBody StockRetrievalRequestDTO stockRetrievalRequestDTO, @RequestHeader Map<String, String> headers){
        log.info("Stock Receive request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.stockRetrieval(stockRetrievalRequestDTO));
    }
    @GetMapping("/lots-available")
    public ResponseEntity<List<LotsResponseDto>> getLotsByStoreAndMedicine( @RequestHeader Map<String, String> headers){
        log.info("Stock lots availability check request  correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        String store = headers.get("storeid");
        Integer storeId = null;
        LotsRequestDTO lotsRequestDTO = new LotsRequestDTO();
        lotsRequestDTO.setMedicineId(Integer.parseInt(headers.get("itemid")));
        if(store!=null && !store.isEmpty()){
            lotsRequestDTO.setStoreId(Integer.parseInt(headers.get("storeid")));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.getLotsByStoreAndMedicine(lotsRequestDTO));
    }

    @PostMapping("/stock-medicine-issue")
    public ResponseEntity<StockRetrievalResponseDTO> stockMedicineIssue(@Valid @RequestBody StockMedicineIssueRequestDTO stockMedicineIssueRequestDTO, @RequestHeader Map<String, String> headers){
        log.info("Stock Medicine Issue request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.stockMedicineIssue(stockMedicineIssueRequestDTO));
    }


    @GetMapping("/get-medicines-with-stock")
    public ResponseEntity<MedicineStockResponseDTO> getMedicinesWithStock(@RequestHeader Map<String, String> headers,
                                                                      @RequestParam(name = "page") int page,
                                                                      @RequestParam(name = "per_page") int perPage,
                                                                      @RequestParam(name = "search", required = false, defaultValue = "") String search
    ){

        StockInquiryRequestDTO stockInqueryRequestDTO = new StockInquiryRequestDTO();
        stockInqueryRequestDTO.setPage(page);
        stockInqueryRequestDTO.setPerPage(perPage);
        stockInqueryRequestDTO.setSearch(search);

        String store = headers.get("storeid");
        if(store!=null && !store.isEmpty()){
            stockInqueryRequestDTO.setStoreId(Integer.parseInt(headers.get("storeid")));
        }
        log.info("User search pagination request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));


        return ResponseEntity.status(HttpStatus.OK).body(stockService.getMedicinesWithStock(stockInqueryRequestDTO));
    }


}
