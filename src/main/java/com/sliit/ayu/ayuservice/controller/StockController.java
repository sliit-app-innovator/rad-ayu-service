package com.sliit.ayu.ayuservice.controller;
import com.sliit.ayu.ayuservice.constants.Constants;
import com.sliit.ayu.ayuservice.dto.StockRetrievalRequestDTO;
import com.sliit.ayu.ayuservice.dto.StockRetrievalResponseDTO;
import com.sliit.ayu.ayuservice.service.StockService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
}
