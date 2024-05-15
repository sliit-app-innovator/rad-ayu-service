package com.sliit.ayu.ayuservice.controller;

import com.sliit.ayu.ayuservice.constants.Constants;
import com.sliit.ayu.ayuservice.dto.StockTransferDTO;
import com.sliit.ayu.ayuservice.service.StockTransferService;
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
public class StockTransferController {

    public static final String STORE_ID = "store-id";
    @Autowired
    private StockTransferService stockTransferService;


    @PostMapping("/stock-transfer")
    public ResponseEntity<StockTransferDTO> addStockTransfer(@Valid @RequestBody StockTransferDTO stockTransferDTO, @RequestHeader Map<String, String> headers){
        log.info("User creation request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.CREATED).body(stockTransferService.requestStockTransfer(stockTransferDTO));
    }

    @GetMapping("/stock-transfer")
    public ResponseEntity<List<StockTransferDTO>> searchStockTransfer(@RequestHeader Map<String, String> headers){
        log.info("User search request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        int storeId = headers.get(STORE_ID) != null ? Integer.parseInt(headers.get(STORE_ID)) : 0;
        return ResponseEntity.status(HttpStatus.OK).body(stockTransferService.searchStockTransferRequest(storeId, headers.get("requested-by")));
    }

    @GetMapping("/stock-transfer/{id}")
    public ResponseEntity<StockTransferDTO> getStockTransfer(@PathVariable int id, @RequestHeader Map<String, String> headers){
        log.info("User get request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
        return ResponseEntity.status(HttpStatus.OK).body(stockTransferService.getStockTransferRequest(id));
    }
}
